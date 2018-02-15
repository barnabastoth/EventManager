package application.api;

import application.model.account.Account;
import application.model.menu.Menu;
import application.repository.MenuRepository;
import application.service.AccountService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuService {

    @Autowired
    AccountService accountService;
    @Autowired RequestUtil requestUtil;
    @Autowired MenuRepository menuRepository;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        Account account = null;
        if(authentication != null) {
            String userEmail = authentication.getName();
            account = accountService.findUserByEmail(userEmail);
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
