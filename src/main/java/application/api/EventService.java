package application.api;


import application.model.account.Account;
import application.model.event.Comment;
import application.model.event.Event;
import application.repository.CommentRepository;
import application.repository.EventRepository;
import application.repository.AccountRepository;
import application.service.AccountService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class EventService {

    @Qualifier("accountRepository") @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @Autowired RequestUtil requestUtil;
    @Autowired private EventRepository eventRepository;
    @Autowired CommentRepository commentRepository;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        Account account = null;
        if(authentication != null) {
            String userEmail = authentication.getName();
            account = accountService.findUserByEmail(userEmail);
        }
        requestUtil.addCommonAttributes(model, account);
    }

    @GetMapping("/api/event/latest")
    public String latestEvent(Model model) {
        model.addAttribute("event", eventRepository.getLatestEvent());
        return Path.Fragment.EVENT;
    }

    @GetMapping("/api/event/{id}")
    public String serveEventByIdPage(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        if(event != null && event.getActive() != 0) {
            model.addAttribute("event", event);
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
        Account account = accountService.findUserByEmail(authentication.getName());
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
        Account account = accountService.findUserByEmail(authentication.getName());
        event.getAccounts().add(account);
        account.addEvent(event);
        eventRepository.saveAndFlush(event);
        accountRepository.saveAndFlush(account);
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/event/{id}/activate")
    public String handleEventActivation(@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findOne(id);
        event.setActive(1);
        eventRepository.saveAndFlush(event);
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return Path.Fragment.ALL_EVENTS;
    }
}
