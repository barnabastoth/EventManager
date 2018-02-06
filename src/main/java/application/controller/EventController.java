package application.controller;

import application.model.Event.Comment;
import application.model.Event.Event;
import application.model.Menu.Menu;
import application.model.Account.Account;
import application.repository.EventRepository;
import application.repository.MenuRepository;
import application.service.UserService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EventController {

    @Qualifier("menuRepository")
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private RequestUtil requestUtil;
    @Qualifier("userRepository")

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        if(authentication != null) {
            String userEmail = authentication.getName();
            Account account = userService.findUserByEmail(userEmail);
            requestUtil.addCommonAttributes(model, account);
        }
    }

    @GetMapping("/")
    public String serveIndexPage(Model model, Comment comment) {
        model.addAttribute("event", eventRepository.getLatestEvent());
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT);
        model.addAttribute("comment", comment);
        return "index";
    }

    @GetMapping("/event/{id}")
    public String serveEventByIdPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT);
        Event event = eventRepository.findOne(id);
        if(event != null) {
            model.addAttribute("event", event);
            return "index";
        }
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/event/new")
    public String serveNewEventPage(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT_MANAGER);
        model.addAttribute("hideId", "true");
        return "index";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/event/new")
    public String handleNewEventCreation(@Valid @ModelAttribute("event") Event event) {
        eventRepository.saveAndFlush(event);
        return "redirect:/event/" + event.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/event/{id}/edit")
    public String serveEditEventPage(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        model.addAttribute("event", event);
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT_MANAGER);
        model.addAttribute("hideId", "false");

        return "index";
    }

}
