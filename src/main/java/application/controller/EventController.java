package application.controller;

import application.model.event.Event;
import application.model.event.NewEvent;
import application.repository.EventRepository;
import application.utils.EventUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired EventRepository eventRepository;
    @Autowired EventUtils eventUtils;

    @GetMapping("/latest")
    public Event serveLatestEvent() {
        return eventRepository.getLatestEvent();
    }

    @GetMapping("/")
    public List<Event> serveAllEvent() {
        System.out.println(eventRepository.findAllByOrderByDateAsc());
        return eventRepository.findAllByOrderByDateAsc();
    }

    @GetMapping("/{id}")
    public Event serveEvent(@PathVariable("id") Long id) { return eventRepository.findOne(id);}

    @PostMapping("/new")
    public int saveEvent(@RequestBody NewEvent newEvent) throws IOException {
        return eventUtils.createNewEvent(newEvent);
    }

}
