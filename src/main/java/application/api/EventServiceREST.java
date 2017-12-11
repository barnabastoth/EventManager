package application.api;


import application.model.Event.Event;
import application.repository.EventRepository;
import application.utils.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventServiceREST {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping(Path.Web.EVENT_BY_ID)
    public Event getEventById(@PathVariable Long id) {
        return eventRepository.getEventByID(id);
    }
}
