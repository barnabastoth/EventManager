package application.utils;

import application.model.User.Account;
import application.model.User.Role;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class RequestUtil {


    public void addCommonAttributes(Model model, Account account) {
        if(account != null) {
            model.addAttribute("loggedInUser", account);
            for (Role role : account.getRoles()) {
                model.addAttribute("loggedInUserRole", role.getRole());
            }
        } else {
            model.addAttribute("userName", "Vendég");
        }
    }

    public void addProfileDetails(Model model, Account account) {
        model.addAttribute("profileId", account.getId());
        model.addAttribute("profileName", account.getName());
        model.addAttribute("profileLastName", account.getLastName());
        model.addAttribute("profileUserName", account.getUserName());
        model.addAttribute("profileEmail", account.getEmail());
        model.addAttribute("profileMemberSince", account.getMemberSince());
        for (Role role : account.getRoles()) {
            model.addAttribute("profileRole", role.getRole());
        }
    }
}
