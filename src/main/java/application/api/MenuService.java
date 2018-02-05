package application.api;

import application.model.Account.Account;
import application.model.Event.Event;
import application.model.Menu.Menu;
import application.repository.EventRepository;
import application.repository.MenuRepository;
import application.utils.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class MenuServiceREST {

    @Qualifier("menuRepository")
    @Autowired
    MenuRepository menuRepository;


    @GetMapping(Path.Api.MENU)
    public String serveMenuPage(Model model, @PathVariable("route") String route) {
        Menu menu = menuRepository.findByRoute(route);
        model.addAttribute("menu", menu);
        return Path.Fragment.MENU;
    }
}
