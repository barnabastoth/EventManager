package application.api;

import application.model.event.Event;
import application.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventService {

    @Autowired EventRepository eventRepository;

    @CrossOrigin
    @GetMapping("/event/latest")
    public Event serveLatestEvent() {
        return eventRepository.getLatestEvent();
    }

    @CrossOrigin
    @GetMapping("/event/")
    public List<Event> serveAllEvent() { return eventRepository.findAll();}

}
