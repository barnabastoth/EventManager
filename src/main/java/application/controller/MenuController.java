package application.controller;

import application.model.Account.Account;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuController {

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

    @GetMapping("/{route}")
    public String serveMenuPage(Model model, @PathVariable("route") String route) {
        Menu menu = menuRepository.findByRoute(route);
        model.addAttribute("account", new Account());
        model.addAttribute("menu", menu);
        model.addAttribute("pageContent", Path.Fragment.MENU);

        if(menu == null) {
            return "redirect:/";
        }
        return "index";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{route}")
    public String handleMenuEdit(@PathVariable("route") String route, @ModelAttribute("menu") Menu menu) {
        menuRepository.saveAndFlush(menu);
        return "redirect:/" + route;
    }
}
