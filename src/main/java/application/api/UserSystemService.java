package application.api;

import application.model.Account.Account;
import application.repository.UserRepository;
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

@Controller
public class UserSystemService {

    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    RequestUtil requestUtil;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        Account account = null;
        if(authentication != null) {
            String userEmail = authentication.getName();
            account = userService.findUserByEmail(userEmail);
        }
        requestUtil.addCommonAttributes(model, account);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/profile/{id}")
    public String serveProfilePage(Model model, @PathVariable("id") int id) {
        Account account = userRepository.findById(id);
        model.addAttribute("account", account);
        return Path.Fragment.PROFILE;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/profile/{id}/edit")
    public String serveProfileEditPage(Model model, Authentication authentication, @PathVariable("id") int id) {
        Account user = userService.findUserByEmail(authentication.getName());
        if(user.getId() == id || user.hasRole("ADMIN")) {
            Account account = userRepository.findById(id);
            model.addAttribute("account", account);
            return Path.Fragment.EDIT_PROFILE;
        }
        return null;
    }
}
