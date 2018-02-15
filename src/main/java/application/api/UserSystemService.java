package application.api;

import application.model.account.Account;
import application.repository.RoleRepository;
import application.repository.AccountRepository;
import application.service.AccountService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserSystemService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @Autowired RequestUtil requestUtil;
    @Autowired RoleRepository roleRepository;

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        Account account = null;
        if(authentication != null) {
            String userEmail = authentication.getName();
            account = accountService.findUserByEmail(userEmail);
        }
        requestUtil.addCommonAttributes(model, account);
    }

    @GetMapping("/api/profile/{id}")
    public String serveProfilePage(Model model, @PathVariable("id") Long id) {
        Account account = accountRepository.findById(id);
        model.addAttribute("account", account);
        return Path.Fragment.PROFILE;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/profile/{id}/edit")
    public String serveProfileEditPage(Model model, Authentication authentication, @PathVariable("id") Long id) {
        Account user = accountService.findUserByEmail(authentication.getName());
        if(user.getId().equals(id) || user.hasRole("ADMIN")) {
            Account account = accountRepository.findById(id);
            model.addAttribute("account", account);
            return Path.Fragment.EDIT_PROFILE;
        }
        return null;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/profile")
    public String serveAllUser(Model model) {
        model.addAttribute("allUsers", accountRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/profile/{id}/activate")
    public String serveUserActivation(@PathVariable("id") Long id, Model model) {
        Account account = accountRepository.findById(id);
        account.setActive(1);
        accountRepository.saveAndFlush(account);
        model.addAttribute("allUsers", accountRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/api/profile/{id}/deactivate")
    public String serveUserDeactivation(@PathVariable("id") Long id, Model model) {
        Account account = accountRepository.findById(id);
        account.setActive(0);
        accountRepository.saveAndFlush(account);
        model.addAttribute("allUsers", accountRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @GetMapping("/api/profile/{id}/admin/add")
    public String serveGiveAdmin(@PathVariable("id") Long id, Model model) {
        Account account = accountRepository.findById(id);
        account.getRoles().add(roleRepository.findByRole("ADMIN"));
        accountRepository.saveAndFlush(account);
        model.addAttribute("allUsers", accountRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @GetMapping("/api/profile/{id}/admin/remove")
    public String serveTakeAdmin(@PathVariable("id") Long id, Model model) {
        Account account = accountRepository.findById(id);
        account.getRoles().remove(roleRepository.findByRole("ADMIN"));
        accountRepository.saveAndFlush(account);
        model.addAttribute("allUsers", accountRepository.findAll());
        return Path.Fragment.ALL_USERS;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/profile/{id}/uploadImg")
    public String serveProfilePicUpload(@PathVariable("id") Long id, Model model) {
        Account account = accountRepository.findById(id);
        model.addAttribute("account", account);
        return Path.Fragment.UPLOAD_PROFILE_IMG;
    }
}
