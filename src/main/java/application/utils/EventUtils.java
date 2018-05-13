package application.utils;

import application.model.authentication.User;
import application.model.event.Event;
import application.model.event.EventField;
import application.model.event.NewEvent;
import application.repository.EventRepository;
import application.repository.EventFieldRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.*;

@Component
public class EventUtils {

    @Autowired EventRepository eventRepository;
    @Autowired EventFieldRepository eventFieldRepository;
    @Autowired UserRepository userRepository;

    public int createNewEvent(NewEvent newEvent) {
        Event event = new Event();
        eventRepository.saveAndFlush(event);
        event.setName(newEvent.getName());
        event.setAddress(newEvent.getAddress());
        event.setDate(ZonedDateTime.parse(newEvent.getDate()).toLocalDateTime());
        event.setDescription(newEvent.getDescription());

        List<EventField> eventFields = new ArrayList<>();
        for (int i = 0; i < newEvent.getFields().size(); i++) {
            EventField eventField = new EventField();
            eventField.setActive(Integer.parseInt(newEvent.getFields().get(i).get("active")));
            eventField.setEvent(event);
            eventField.setIcon(newEvent.getFields().get(i).get("icon"));
            eventField.setSubText(newEvent.getFields().get(i).get("subText"));
            eventField.setText(newEvent.getFields().get(i).get("text"));
            eventFields.add(eventField);
        }
        event.setFields(eventFields);

        Set<User> speakers = new HashSet<>();
        for (int i = 0; i < newEvent.getSpeakers().size(); i++) {
            Optional<User> speaker = userRepository.findById(Long.parseLong(newEvent.getSpeakers().get(i).get("id")));
            speaker.ifPresent(speakers::add);
        }
        event.setSpeakers(speakers);

        event.getSettings().put("Active", newEvent.getSettings().get("active"));


        System.out.println(event.getDate());
        System.out.println(newEvent.getDate());

        eventRepository.saveAndFlush(event);

        return (int) (long) event.getId();
    }

    public void saveEditedEvent (NewEvent newEvent) {
        Optional<Event> event = eventRepository.findById(newEvent.getId());
        event.ifPresent(event1 -> {
            event1.setName(newEvent.getName());
            event1.setAddress(newEvent.getAddress());
            event1.setDate(ZonedDateTime.parse(newEvent.getDate()).toLocalDateTime());
            event1.setDescription(newEvent.getDescription());

            event1.getFields().clear();
            event1.getSpeakers().clear();

            List<EventField> eventFields = event1.getFields();
            for (int i = 0; i < newEvent.getFields().size(); i++) {
                EventField eventField = eventFieldRepository.getOne(Long.parseLong(newEvent.getFields().get(i).get("id")));
                eventField.setActive(Integer.parseInt(newEvent.getFields().get(i).get("active")));
                eventField.setIcon(newEvent.getFields().get(i).get("icon"));
                eventField.setSubText(newEvent.getFields().get(i).get("subText"));
                eventField.setText(newEvent.getFields().get(i).get("text"));
                eventFields.add(eventField);
            }
            event1.setFields(eventFields);

            Set<User> speakers = new HashSet<>();
            for (int i = 0; i < newEvent.getSpeakers().size(); i++) {
                Optional<User> speaker = userRepository.findById(Long.parseLong(newEvent.getSpeakers().get(i).get("id")));
                speaker.ifPresent(speakers::add);
            }
            event1.setSpeakers(speakers);

            event1.getSettings().put("Active", newEvent.getSettings().get("active"));
            System.out.println(event1.getFields());
            eventRepository.saveAndFlush(event1);
        });
    }

}

