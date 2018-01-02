package application.controller;

import application.model.Event.Event;
import application.model.User.Account;
import application.repository.EventRepository;
import application.service.UserService;
import application.utils.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"user"})
public class IndexController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public String user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = userService.findUserByEmail(auth.getName());
        System.out.println(account);
        if(account == null) {
            return "Vendég";
        }
        return account.getName();
    }

    @GetMapping(Path.Web.INDEX)
    public String serveIndexPage(Model model) {
        model.addAttribute("event", eventRepository.getLatestEvent());
        model.addAttribute("account", new Account());
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
