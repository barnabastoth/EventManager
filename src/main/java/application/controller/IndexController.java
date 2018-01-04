package application.controller;

import application.model.Event.Event;
import application.model.Menu.Menu;
import application.model.User.Account;
import application.repository.EventRepository;
import application.repository.MenuRepository;
import application.repository.UserRepository;
import application.service.UserService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

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
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        if(authentication != null) {
            String userEmail = authentication.getName();
            Account account = userService.findUserByEmail(userEmail);
            requestUtil.addCommonAttributes(model, account);
        }
    }

    @GetMapping(Path.Web.INDEX)
    public String serveIndexPage(Model model, Authentication authentication) {
        model.addAttribute("event", eventRepository.getLatestEvent());
        model.addAttribute("account", new Account());
        return Path.Template.INDEX;
    }

    @GetMapping(Path.Web.EVENT_BY_ID)
    public String getEventById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("account", new Account());
        Event event = eventRepository.findOne(id);
        if(event != null) {
            model.addAttribute("event", event);
            return Path.Template.INDEX;
        }
        return "redirect:/";
    }

    @GetMapping(Path.Web.MENU)
    public String serveMenupage(Model model, @PathVariable("route") String route) {
        List<Menu> menu = menuRepository.findByRoute(route);
        model.addAttribute("account", new Account());
        model.addAttribute("menu", menu.get(0));

        if(menu != null) {
            return Path.Template.MENU;
        }
        return Path.Template.INDEX;
    }

    @PostMapping(Path.Web.MENU)
    public RedirectView handleMenuEdit( @PathVariable("route") String route, @ModelAttribute("menu") Menu menu) {
        System.out.println(menu.getTitle());
        System.out.println(route);
        menuRepository.save(menu);
        return new RedirectView("/" + route);
    }


}
