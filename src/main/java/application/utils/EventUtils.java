package application.utils;

import application.model.event.Event;
import application.model.event.Field;
import application.model.event.NewEvent;
import application.repository.EventRepository;
import application.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class EventUtils {

    @Autowired EventRepository eventRepository;
    @Autowired FieldRepository fieldRepository;

    public void createNewEvent(NewEvent newEvent) {
        Event event = new Event();
        eventRepository.saveAndFlush(event);
        Map<String, Field> fields = new HashMap<>();
        fields.put("title", new Field(newEvent.getTitle().get("text"), Integer.parseInt(newEvent.getTitle().get("active")), event));
        fields.put("name", new Field(newEvent.getName().get("text"), Integer.parseInt(newEvent.getName().get("active")), event));
        fields.put("address", new Field(newEvent.getAddress().get("text"), Integer.parseInt(newEvent.getAddress().get("active")), event));
        fields.put("description", new Field(newEvent.getDescription().get("text"), Integer.parseInt(newEvent.getDescription().get("active")), event));
        fields.put("price", new Field(newEvent.getPrice().get("text"), Integer.parseInt(newEvent.getPrice().get("active")), event));
        fields.put("duration", new Field(newEvent.getDuration().get("text"), Integer.parseInt(newEvent.getDuration().get("active")), event));
        fields.put("ticketLink", new Field(newEvent.getTicketLink().get("text"), Integer.parseInt(newEvent.getTicketLink().get("active")), event));
//        event.setDate(new Field(newEvent.getDate().get("text"), Integer.parseInt(newEvent.getDate().get("active"))));
        event.setActive(newEvent.getActive());
        event.setFields(fields);
        eventRepository.saveAndFlush(event);
    }
}
