package application.controller;

import application.model.Event.Event;
import application.model.Menu.Menu;
import application.model.User.Account;
import application.repository.EventRepository;
import application.repository.MenuRepository;
import application.service.UserService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class IndexController {

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

    @GetMapping(Path.Web.INDEX)
    public String serveIndexPage(Model model) {
        model.addAttribute("event", eventRepository.getLatestEvent());
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT);
        return Path.Template.INDEX;
    }

    @GetMapping(Path.Web.EVENT_BY_ID)
    public String serveEventByIdPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT);
        Event event = eventRepository.findOne(id);
        if(event != null) {
            model.addAttribute("event", event);
            return Path.Template.INDEX;
        }
        return "redirect:/";
    }

    @GetMapping(Path.Web.MENU)
    public String serveMenuPage(Model model, @PathVariable("route") String route) {
        Menu menu = menuRepository.findByRoute(route);
        model.addAttribute("account", new Account());
        model.addAttribute("menu", menu);
        model.addAttribute("pageContent", Path.Fragment.MENU);

        if(menu == null) {
            return "redirect:/";
        }
        return Path.Template.INDEX;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(Path.Web.MENU)
    public String handleMenuEdit(@PathVariable("route") String route, @ModelAttribute("menu") Menu menu) {
        menuRepository.saveAndFlush(menu);
        return "redirect:/" + route;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(Path.Web.NEW_EVENT)
    public String serveNewEventPage(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.NEW_EVENT);
        return Path.Template.INDEX;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(Path.Web.NEW_EVENT)
    public String handleNewEventCreation(@ModelAttribute("event") Event event) {
        eventRepository.saveAndFlush(event);
        return "redirect:/event/" + event.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(Path.Web.EDIT_EVENT)
    public String serveEditEventPage(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        model.addAttribute("event", event);
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EDIT_EVENT);

        return Path.Template.INDEX;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(Path.Web.ADD_EVENT_SPEAKERS)
    public String serveAddEventSpeakersPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.ADD_EVENT_SPEAKERS);
        model.addAttribute("eventId", id);
        Event event = eventRepository.findOne(id);
        StringBuilder speakers = new StringBuilder();
        for (Account account : event.getSpeakers()) {
            speakers.append(account.getEmail()).append(',');
        }
        if(event.getSpeakers().size() > 1) {
            speakers = new StringBuilder(StringUtils.substring(speakers.toString(), 0, speakers.length() - 1));
        }
        model.addAttribute("speakers", speakers.toString());
        return Path.Template.INDEX;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(Path.Web.ADD_EVENT_SPEAKERS)
    public String handleAddEventSpeakersPage(@PathVariable("id") Long id, @RequestParam("speakers") String speakersInput) {
        Event event = eventRepository.findOne(id);
        Set<Account> speakers = event.getSpeakers();
        String[] emails = speakersInput.split(",");
        for (String email : emails) {
            speakers.add(userService.findUserByEmail(email));
        }
        try {
            eventRepository.saveAndFlush(event);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/event/" + id;
    }

}
