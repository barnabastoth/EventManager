package application.utils;

import application.model.authentication.User;
import application.model.event.Event;
import application.model.event.Field;
import application.model.event.NewEvent;
import application.repository.EventRepository;
import application.repository.FieldRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < newEvent.getFields().size(); i++) {
            Field field = new Field();
            field.setActive(Integer.parseInt(newEvent.getFields().get(i).get("active")));
            field.setEvent(event);
            field.setIcon(newEvent.getFields().get(i).get("icon"));
            field.setSubText(newEvent.getFields().get(i).get("subText"));
            field.setText(newEvent.getFields().get(i).get("text"));
            fields.add(field);
        }
        event.setFields(fields);

        Set<User> speakers = new HashSet<>();
        for (int i = 0; i < newEvent.getSpeakers().size(); i++) {
            speakers.add(userRepository.findOne(Long.parseLong(newEvent.getSpeakers().get(i).get("id"))));
        }
        event.setSpeakers(speakers);

        event.setActive(Integer.parseInt(newEvent.getSettings().get("active")));

        eventRepository.saveAndFlush(event);

        return (int) (long) event.getId();
        }
}

