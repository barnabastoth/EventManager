package application.controller;

import application.repository.EventRepository;
import application.utils.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping(Path.Web.INDEX)
    public String serveIndexPage(Model model) {
        model.addAttribute("latestEvent", eventRepository.getLatestEvent());
        return Path.Template.INDEX;
    }
}
