package application.utils;

import application.model.event.Event;
import application.model.event.Field;
import application.model.event.NewEvent;
import application.repository.EventRepository;
import application.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventUtils {

    @Autowired EventRepository eventRepository;
    @Autowired FieldRepository fieldRepository;

    public void saveEvent(NewEvent newEvent) {
        Event event = new Event();
        System.out.println(newEvent.getTitle());
        Field fieldTitle = new Field(newEvent.getTitle().get("text"), Integer.parseInt(newEvent.getTitle().get("active")));
        fieldRepository.saveAndFlush(fieldTitle);
        event.setTitle(fieldTitle);
//        event.setName(new Field(newEvent.getName().get("text"), Integer.parseInt(newEvent.getName().get("active"))));
//        event.setAddress(new Field(newEvent.getAddress().get("text"), Integer.parseInt(newEvent.getAddress().get("active"))));
//        event.setDescription(new Field(newEvent.getDescription().get("text"), Integer.parseInt(newEvent.getDescription().get("active"))));
//        event.setPrice(new Field(newEvent.getPrice().get("text"), Integer.parseInt(newEvent.getPrice().get("active"))));
//        event.setDuration(new Field(newEvent.getDuration().get("text"), Integer.parseInt(newEvent.getDuration().get("active"))));
//        event.setTicketLink(new Field(newEvent.getTicketLink().get("text"), Integer.parseInt(newEvent.getTicketLink().get("active"))));
////        event.setDate(new Field(newEvent.getDate().get("text"), Integer.parseInt(newEvent.getDate().get("active"))));
//        event.setActive(newEvent.getActive());
//        System.out.println(event.getTitle().getText());
        eventRepository.saveAndFlush(event);
    }
}
