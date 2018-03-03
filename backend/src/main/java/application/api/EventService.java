package application.api;

import application.model.event.Event;
import application.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EventService {

    @Autowired EventRepository eventRepository;

    @GetMapping("/event/latest")
    public Event serveLatestEvent() {
        return eventRepository.getLatestEvent();
    }

    @GetMapping("/event/")
    public List<Event> serveAllEvent() { return eventRepository.findAll();}

    @GetMapping("/event/{id}")
    public Event serveAllEvent(@PathVariable("id") Long id) { return eventRepository.findOne(id);}

}
