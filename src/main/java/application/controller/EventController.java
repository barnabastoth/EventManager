package application.controller;

import application.model.Event.Event;
import application.model.Menu.Menu;
import application.model.Account.Account;
import application.repository.EventRepository;
import application.repository.MenuRepository;
import application.service.UserService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping(Path.Web.LATEST_EVENT)
    public String latestEvent(Model model) {
        model.addAttribute("event", eventRepository.getLatestEvent());
        return Path.Fragment.EVENT;
    }

    @GetMapping(Path.Web.EVENT_BY_ID)
    public String serveEventByIdPage(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        if(event != null) {
            model.addAttribute("event", event);
            return Path.Fragment.EVENT;
        }
        return "redirect:/";
    }

    @GetMapping(Path.Web.MENU)
    public String serveMenuPage(Model model, @PathVariable("route") String route) {
        Menu menu = menuRepository.findByRoute(route);
        model.addAttribute("menu", menu);
        return Path.Fragment.MENU;
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
        model.addAttribute("pageContent", Path.Fragment.EVENT_MANAGER);
        model.addAttribute("hideId", "true");
        return Path.Template.INDEX;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(Path.Web.NEW_EVENT)
    public String handleNewEventCreation(@Valid @ModelAttribute("event") Event event) {
        System.out.println(event.getTitle());
        eventRepository.saveAndFlush(event);
        return "redirect:/event/" + event.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(Path.Web.EDIT_EVENT)
    public String serveEditEventPage(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        model.addAttribute("event", event);
        model.addAttribute("account", new Account());
        model.addAttribute("pageContent", Path.Fragment.EVENT_MANAGER);
        model.addAttribute("hideId", "false");

        return Path.Template.INDEX;
    }
}
