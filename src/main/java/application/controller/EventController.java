package application.controller;

import application.model.authentication.User;
import application.model.event.Comment;
import application.model.event.Event;
import application.model.event.NewComment;
import application.model.event.NewEvent;
import application.repository.CommentRepository;
import application.repository.EventRepository;
import application.repository.UserRepository;
import application.utils.AuthenticationUtils;
import application.utils.EventUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired EventRepository eventRepository;
    @Autowired EventUtils eventUtils;
    @Autowired UserRepository userRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired AuthenticationUtils authenticationUtils;


//    @GetMapping("/")
//    public List<Event> serveAllEvent(Principal principal) {
//        Boolean isUserAnAdminOrOwner = authenticationUtils.isUserAnAdminOrOwner(principal);
//        System.out.println(isUserAnAdminOrOwner);
//        return isUserAnAdminOrOwner ? eventRepository.findAll() : eventRepository.getActiveEvents();
//    }
//
//    @GetMapping("/{id}")
//    public Event serveEventById(@PathVariable("id") Long id, Principal principal) {
//        Boolean isUserAnAdminOrOwner = authenticationUtils.isUserAnAdminOrOwner(principal);
//        System.out.println(isUserAnAdminOrOwner);
//        return isUserAnAdminOrOwner ? eventRepository.findById(id).orElse(null) : eventRepository.getActiveEventById(id).orElse(null);
//    }

    @GetMapping("/")
    public List<Event> serveAllEvent(Principal principal) {
        if(principal != null) {
            Optional<User> user = userRepository.findByUsername(principal.getName());
            if(user.isPresent()) {
                if(user.get().getRole().getRole().equals("Tulajdonos") || user.get().getRole().getRole().equals("Admin")) {
                    return eventRepository.findAll();
                }
                return eventRepository.getActiveEvents();
            }
        }
        return eventRepository.getActiveEvents();
    }

    @GetMapping("/{id}")
    public Event serveEventById(@PathVariable("id") Long id, Principal principal) {
        if(principal != null) {
            Optional<User> user = userRepository.findByUsername(principal.getName());
            if(user.isPresent()) {
                if(user.get().getRole().getRole().equals("Tulajdonos") || user.get().getRole().getRole().equals("Admin")) {
                    System.out.println("OWNER");
                    return eventRepository.findById(id).orElse(null);
                }
                System.out.println("NOPE");
                return eventRepository.getActiveEventById(id).orElse(null);
            }
        }
        System.out.println("NOPEE");
        return eventRepository.getActiveEventById(id).orElse(null);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @PostMapping("/edit")
    public void editEvent(@RequestBody NewEvent newEvent) { eventUtils.saveEditedEvent(newEvent); }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @GetMapping("/{id}/delete")
    public void deleteEvent(@PathVariable("id") Long id) { eventRepository.findById(id).ifPresent(event1 -> eventRepository.delete(event1)); }

    @GetMapping("/{id}/attend")
    public Set<User> attendEvent(@PathVariable("id") Long id, Principal principal) {
        if(principal != null) {
            Optional<User> user = userRepository.findByUsername(principal.getName());
            Optional<Event> event = eventRepository.findById(id);
            if(user.isPresent() && event.isPresent()) {
                event.get().getAttendees().add(user.get());
                eventRepository.saveAndFlush(event.get());
                return event.get().getAttendees();
            }
        }
        return null;
    }

    @GetMapping("/{id}/unattend")
    public Set<User> unAttendEvent(@PathVariable("id") Long id, Principal principal) {
        if(principal != null) {
            Optional<User> user = userRepository.findByUsername(principal.getName());
            Optional<Event> event = eventRepository.findById(id);
            if(user.isPresent() && event.isPresent()) {
                event.get().getAttendees().remove(user.get());
                eventRepository.saveAndFlush(event.get());
                return event.get().getAttendees();
            }
        }
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/new")
    public void saveNewComment (@RequestBody NewComment newComment) {
        Optional<User> user = userRepository.findById(newComment.getEventId());
        Optional<Event> event = eventRepository.findById(newComment.getEventId());

        Comment comment = new Comment();
        comment.setDate(LocalDateTime.now());
        comment.setMessage(newComment.getMessage());

        if(event.isPresent() && user.isPresent()) {
            comment.setUser(user.get());
            comment.setEvent(event.get());
            user.get().getComments().add(comment);
            event.get().getComments().add(comment);
            userRepository.saveAndFlush(user.get());
            commentRepository.saveAndFlush(comment);
            eventRepository.saveAndFlush(event.get());
        }


    }

}
