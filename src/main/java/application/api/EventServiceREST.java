package application.api;


import application.model.Event.Event;
import application.repository.EventRepository;
import application.utils.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestController
public class EventServiceREST {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping(Path.Api.EVENT_BY_ID)
    public Event getEventById(@PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        System.out.println(event);
        if(event != null) {
            return event;
        }
        return eventRepository.getLatestEvent();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/all")
    public String secure() {
        return "secured hello";
    }
}
