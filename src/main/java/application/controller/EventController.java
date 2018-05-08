package application.controller;

import application.model.event.Event;
import application.model.event.NewEvent;
import application.repository.EventRepository;
import application.utils.EventUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired EventRepository eventRepository;
    @Autowired EventUtils eventUtils;

    @GetMapping("/")
    public List<Event> serveAllEvent() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public Event serveEvent(@PathVariable("id") Long id) {
        Optional<Event> event = eventRepository.findById(id);
        System.out.println(event.get().getSpeakers());
        return event.orElseGet(event::get);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @PostMapping("/new")
    public int saveEvent(@RequestBody NewEvent newEvent) throws IOException {
        return eventUtils.createNewEvent(newEvent);
    }

}
