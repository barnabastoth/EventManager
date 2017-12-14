package application.controller;

import application.model.Event.Event;
import application.repository.EventRepository;
import application.utils.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping(Path.Web.INDEX)
    public String serveIndexPage(Model model) {
        model.addAttribute("event", eventRepository.getLatestEvent());
        return Path.Template.INDEX;
    }

    @GetMapping(Path.Web.EVENT_BY_ID)
    public String getEventById(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        if(event != null) {
            model.addAttribute("event", event);
            return Path.Template.INDEX;
        }
        return "redirect:/";
    }
}
