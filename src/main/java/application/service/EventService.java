package application.service;

import application.model.authentication.User;
import application.model.event.Event;
import application.model.event.EventField;
import application.model.event.NewEvent;
import application.repository.EventRepository;
import application.repository.EventFieldRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class EventService {

    @Autowired EventRepository eventRepository;
    @Autowired EventFieldRepository eventFieldRepository;
    @Autowired UserRepository userRepository;

    public Long createNewEvent(NewEvent newEvent) {
        Event event = new Event();
        eventRepository.saveAndFlush(event);
        event.setName(newEvent.getName());
        event.setAddress(newEvent.getAddress());
        if(newEvent.getDate().length() > 0) {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS[xxx][xx][X]");
            event.setDate(LocalDateTime.parse(newEvent.getDate(), formatter));
        } else {
            event.setDate(LocalDateTime.of(2080, 4, 20, 1, 30));
        }
        event.setDescription(newEvent.getDescription());
        event.setActive(newEvent.getActive());
        event.setLatitude(newEvent.getLatitude());
        event.setLongitude(newEvent.getLongitude());
        event.setShortDescription(newEvent.getShortDescription());

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

        eventRepository.saveAndFlush(event);

        return event.getId();
    }

    public void saveEditedEvent (NewEvent newEvent) {
        Optional<Event> event = eventRepository.findById(newEvent.getId());
        event.ifPresent(event1 -> {
            event1.setName(newEvent.getName());
            event1.setAddress(newEvent.getAddress());
            if(newEvent.getDate().length() > 0) {
                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS[xxx][xx][X]");
                event1.setDate(LocalDateTime.parse(newEvent.getDate(), formatter));
            } else {
                event1.setDate(LocalDateTime.of(2080, 4, 20, 1, 30));
            }
            event1.setDescription(newEvent.getDescription());

            if(event1.getActive() == null) {
                event1.setActive(0);
            } else {
                event1.setActive(newEvent.getActive());
            }

            event1.setLatitude(newEvent.getLatitude());
            event1.setLongitude(newEvent.getLongitude());
            event1.setShortDescription(newEvent.getShortDescription());
            event1.getFields().clear();
            event1.getSpeakers().clear();

            List<EventField> eventFields = event1.getFields();
            for (int i = 0; i < newEvent.getFields().size(); i++) {
                EventField eventField;
                if(newEvent.getFields().get(i).get("id") != null) {
                    eventField = eventFieldRepository.getOne(Long.parseLong(newEvent.getFields().get(i).get("id")));
                } else {
                    eventField = new EventField();
                }
                eventField.setActive(Integer.parseInt(newEvent.getFields().get(i).get("active")));
                eventField.setIcon(newEvent.getFields().get(i).get("icon"));
                eventField.setSubText(newEvent.getFields().get(i).get("subText"));
                eventField.setText(newEvent.getFields().get(i).get("text"));
                eventField.setEvent(event1);
                eventFields.add(eventField);

            }
            event1.setFields(eventFields);

            Set<User> speakers = new HashSet<>();
            for (int i = 0; i < newEvent.getSpeakers().size(); i++) {
                Optional<User> speaker = userRepository.findById(Long.parseLong(newEvent.getSpeakers().get(i).get("id")));
                speaker.ifPresent(speakers::add);
            }
            event1.setSpeakers(speakers);

            eventRepository.saveAndFlush(event1);
        });
    }

}

