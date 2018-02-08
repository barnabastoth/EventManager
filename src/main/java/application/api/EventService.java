package application.api;


import application.model.Account.Account;
import application.model.Event.Comment;
import application.model.Event.Event;
import application.repository.CommentRepository;
import application.repository.EventRepository;
import application.repository.UserRepository;
import application.service.UserService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class EventService {

    @Qualifier("userRepository") @Autowired UserRepository userRepository;
    @Autowired UserService userService;
    @Autowired RequestUtil requestUtil;
    @Autowired private EventRepository eventRepository;
    @Autowired CommentRepository commentRepository;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        Account account = null;
        if(authentication != null) {
            String userEmail = authentication.getName();
            account = userService.findUserByEmail(userEmail);
        }
        requestUtil.addCommonAttributes(model, account);
    }

    @GetMapping("/api/event/latest")
    public String latestEvent(Model model) {
        model.addAttribute("event", eventRepository.getLatestEvent());
        model.addAttribute("eventSpeakers", userService.getSpeakersByEmail(eventRepository.getLatestEvent().getSpeakers()));
        return Path.Fragment.EVENT;
    }

    @GetMapping("/api/event/{id}")
    public String serveEventByIdPage(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        if(event != null) {
            model.addAttribute("event", event);
            model.addAttribute("eventSpeakers", userService.getSpeakersByEmail(eventRepository.getLatestEvent().getSpeakers()));
            System.out.println("ASD" + event.getComments().toString());
            return Path.Fragment.EVENT;
        }
        return "redirect:/api/event/latest";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/event/new")
    public String serveNewEvent(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("hideId", "true");
        return Path.Fragment.EVENT_MANAGER;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/event/{id}/edit")
    public String serveEditEvent(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        model.addAttribute("event", event);
        model.addAttribute("hideId", "false");

        return Path.Fragment.EVENT_MANAGER;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/api/event/{id}/comment/new")
    public String handleNewEventComment(@PathVariable("id") Long id, Authentication authentication, @RequestBody String commentMessage) {
        Comment comment = new Comment();
        Event event = eventRepository.findOne(id);
        Account account = userService.findUserByEmail(authentication.getName());
        comment.setMessage(commentMessage);
        comment.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        comment.setAccount(account);
        comment.setEvent(event);
        event.getComments().add(comment);

        commentRepository.saveAndFlush(comment);
        return "redirect:/";
    }

    @GetMapping(value = "/api/event/{id}/comment")
    public String serveEventComments(@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findOne(id);
        if(event != null) {
            model.addAttribute("event", event);
            return Path.Fragment.EVENT_COMMENT;
        }
        return "redirect:/";
    }

    @GetMapping(value = "/api/event")
    public String serveAllEvent(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return Path.Fragment.ALL_EVENTS;
    }

    @PostMapping(value = "/api/event/{id}/attend")
    public String handleAttend(Authentication authentication, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        Account account = userService.findUserByEmail(authentication.getName());
        event.getAccounts().add(account);
        account.addEvent(event);
        eventRepository.saveAndFlush(event);
        userRepository.saveAndFlush(account);
        return "redirect:/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/event/{id}/deactivate")
    public String handleEventDeactivation(@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findOne(id);
        event.setActive(0);
        eventRepository.saveAndFlush(event);
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return Path.Fragment.ALL_EVENTS;
    }
}
