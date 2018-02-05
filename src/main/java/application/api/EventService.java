package application.api;


import application.model.Account.Account;
import application.model.Event.Event;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class EventServiceREST {

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

    @GetMapping(Path.Api.LATEST_EVENT)
    public String latestEvent(Model model) {
        model.addAttribute("event", eventRepository.getLatestEvent());
        return Path.Fragment.EVENT;
    }

    @GetMapping(Path.Api.EVENT_BY_ID)
    public String serveEventByIdPage(Model model, @PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        if(event != null) {
            model.addAttribute("event", event);
            return Path.Fragment.EVENT;
        }
        return "redirect:/";
    }
}
