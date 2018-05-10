package application.utils;

import application.model.authentication.User;
import application.model.event.Event;
import application.model.event.EventField;
import application.model.event.NewEvent;
import application.repository.EventRepository;
import application.repository.FieldRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

@Component
public class EventUtils {

    @Autowired EventRepository eventRepository;
    @Autowired FieldRepository fieldRepository;
    @Autowired UserRepository userRepository;

    public int createNewEvent(NewEvent newEvent) {
        Event event = new Event();
        eventRepository.saveAndFlush(event);
        event.setName(newEvent.getBasicInfo().get("name"));
        event.setAddress(newEvent.getBasicInfo().get("address"));
//        event.setDate(newEvent.getBasicInfo().get("date"));
        event.setDescription(newEvent.getBasicInfo().get("description"));

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

        event.setDate(ZonedDateTime.parse(newEvent.getBasicInfo().get("date")).toLocalDateTime());
        System.out.println(event.getDate());
        System.out.println(newEvent.getBasicInfo().get("date"));

        eventRepository.saveAndFlush(event);

        return (int) (long) event.getId();
        }
}

