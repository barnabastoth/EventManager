package application.controller;

import application.model.User.Account;
import application.repository.EventRepository;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserSystemController {
	
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
		Account account = null;
		if(authentication != null) {
			String userEmail = authentication.getName();
			account = userService.findUserByEmail(userEmail);
		}
		requestUtil.addCommonAttributes(model, account);
	}

	@PreAuthorize("isAnonymous()")
	@GetMapping(Path.Web.LOGIN)
	public String serveLoginPage(Model model) {
		model.addAttribute("event", eventRepository.getLatestEvent());
		model.addAttribute("account", new Account());
		model.addAttribute("authentication", "login");
        model.addAttribute("pageContent", Path.Fragment.EVENT);
		return Path.Template.INDEX;
	}

	@PreAuthorize("isAnonymous()")
	@GetMapping(Path.Web.REGISTRATION)
	public String serveRegistrationPage(Model model){
		model.addAttribute("event", eventRepository.getLatestEvent());
		model.addAttribute("account", new Account());
		model.addAttribute("authentication", "registration");
        model.addAttribute("pageContent", Path.Fragment.EVENT);
		return Path.Template.INDEX;
	}

	@PreAuthorize("isAnonymous()")
	@PostMapping(Path.Web.REGISTRATION)
	public String handleRegistration(Model model,
                                     @Valid @ModelAttribute Account account,
                                     BindingResult bindingResult) {
		Account accountExists = userService.findUserByEmail(account.getEmail());
		model.addAttribute("account", new Account());
		if (accountExists != null) {
			bindingResult
					.rejectValue("email", "error.account",
							"There is already a account registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
		} else {
			userService.saveUser(account);
			model.addAttribute("successMessage", "Account has been registered successfully");
		}
		return "redirect:/login";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(Path.Web.PROFILE)
	public String serveProfilePage (Model model, @PathVariable("id") int id) {
		Account account = userRepository.findById(id);
        model.addAttribute("pageContent", Path.Fragment.PROFILE);
		model.addAttribute("account", account);
		requestUtil.addProfileDetails(model, account);
		return Path.Template.INDEX;
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping(Path.Web.PROFILE)
	public String handleProfileEdit(@PathVariable("id") int id, @ModelAttribute Account account) {
		userRepository.saveAndFlush(account);
		return "redirect:/profile/" + Integer.toString(id);
	}
}
