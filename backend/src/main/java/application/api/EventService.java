package application.api;

import application.model.event.Event;
import application.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/event")
public class EventService {

    @Autowired EventRepository eventRepository;

    @GetMapping("/latest")
    public Event serveLatestEvent() {
        return eventRepository.getLatestEvent();
    }

    @GetMapping("/")
    public List<Event> serveAllEvent() { return eventRepository.findAll();}

    @GetMapping("/{id}")
    public Event serveAllEvent(@PathVariable("id") Long id) { return eventRepository.findOne(id);}

}
