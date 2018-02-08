package application.api;

import application.model.Account.Account;
import application.model.Account.Role;
import application.repository.RoleRepository;
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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class UserSystemService {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;
    @Autowired RequestUtil requestUtil;
    @Autowired RoleRepository roleRepository;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        Account account = null;
        if(authentication != null) {
            String userEmail = authentication.getName();
            account = userService.findUserByEmail(userEmail);
        }
        requestUtil.addCommonAttributes(model, account);
    }

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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/profile")
    public String serveAllUser(Model model) {
        model.addAttribute("allUsers", userRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/profile/{id}/activate")
    public String serveUserActivation(@PathVariable("id") int id, Model model) {
        Account account = userRepository.findById(id);
        account.setActive(1);
        userRepository.saveAndFlush(account);
        model.addAttribute("allUsers", userRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/profile/{id}/deactivate")
    public String serveUserDeactivation(@PathVariable("id") int id, Model model) {
        Account account = userRepository.findById(id);
        account.setActive(0);
        userRepository.saveAndFlush(account);
        model.addAttribute("allUsers", userRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @GetMapping("/api/profile/{id}/admin/add")
    public String serveGiveAdmin(@PathVariable("id") int id, Model model) {
        Account account = userRepository.findById(id);
        account.getRoles().add(roleRepository.findByRole("ADMIN"));
        userRepository.saveAndFlush(account);
        model.addAttribute("allUsers", userRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @GetMapping("/api/profile/{id}/admin/remove")
    public String serveTakeAdmin(@PathVariable("id") int id, Model model) {
        Account account = userRepository.findById(id);
        account.getRoles().remove(roleRepository.findByRole("ADMIN"));
        userRepository.saveAndFlush(account);
        model.addAttribute("allUsers", userRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @GetMapping("/rights/owner")
    public String onlyForOwners() {
        return "index";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ADMIN')")
    @GetMapping("/rights/admin")
    public String onlyForAdmins() {
        return "index";
    }
}
