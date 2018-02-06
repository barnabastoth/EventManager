package application.api;

import application.model.Account.Account;
import application.model.Event.Event;
import application.model.Menu.Menu;
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
public class MenuService {

    @Autowired
    UserService userService;
    @Autowired
    RequestUtil requestUtil;
    @Qualifier("menuRepository")
    @Autowired
    MenuRepository menuRepository;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        Account account = null;
        if(authentication != null) {
            String userEmail = authentication.getName();
            account = userService.findUserByEmail(userEmail);
        }
        requestUtil.addCommonAttributes(model, account);
    }

    @GetMapping("/api/{route}")
    public String serveMenuPage(Model model, @PathVariable("route") String route) {
        Menu menu = menuRepository.findByRoute(route);
        model.addAttribute("account", new Account());
        model.addAttribute("menu", menu);
        model.addAttribute("pageContent", Path.Fragment.MENU);

        if(menu == null) {
            return "redirect:/";
        }
        return Path.Fragment.MENU;
    }
}
