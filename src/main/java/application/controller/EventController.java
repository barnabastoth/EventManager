package application.controller;

import application.model.Event.Comment;
import application.model.Event.Event;
import application.model.Menu.Menu;
import application.model.Account.Account;
import application.repository.EventRepository;
import application.repository.MenuRepository;
import application.repository.UserRepository;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class EventController {

    @Autowired private MenuRepository menuRepository;
    @Autowired private UserService userService;
    @Autowired private EventRepository eventRepository;
    @Autowired private RequestUtil requestUtil;
    @Autowired private UserRepository userRepository;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        if(authentication != null) {
            String userEmail = authentication.getName();
            Account account = userService.findUserByEmail(userEmail);
            requestUtil.addCommonAttributes(model, account);
        }
    }

    @GetMapping("/")
    public String serveIndexPage(Model model, Authentication authentication, HttpServletRequest httpServletRequest) throws ServletException {
        if(authentication != null) {
            if(userService.findUserByEmail(authentication.getName()).getActive() == 0) {
                httpServletRequest.logout();
            }
        }
        model.addAttribute("event", eventRepository.getLatestEvent());
        model.addAttribute("eventSpeakers", userService.getSpeakersByEmail(eventRepository.getLatestEvent().getSpeakers()));
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT);
        return "index";
    }

    @GetMapping("/event/{id}")
    public String serveEventByIdPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT);
        model.addAttribute("eventSpeakers", userService.getSpeakersByEmail(eventRepository.getLatestEvent().getSpeakers()));
        Event event = eventRepository.findOne(id);
        if(event != null) {
            model.addAttribute("event", event);
            return "index";
        }
        return "redirect:/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/event/new")
    public String serveNewEventPage(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT_MANAGER);
        model.addAttribute("hideId", "true");
        return "index";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @PostMapping("/event/new")
    public String handleNewEventCreation(@Valid @ModelAttribute("event") Event event) {
        event.setActive(1);
        eventRepository.saveAndFlush(event);
        return "redirect:/event/" + event.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/event/{id}/edit")
    public String serveEditEventPage(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        model.addAttribute("event", event);
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT_MANAGER);
        model.addAttribute("hideId", "false");
        return "index";
    }

    @GetMapping("/event")
    public String serveAllEvent(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.ALL_EVENTS);
        return "index";
    }

}
