package application.controller;

import application.model.User.Account;
import application.repository.EventRepository;
import application.service.UserService;
import application.utils.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EventRepository eventRepository;

	@GetMapping(Path.Web.LOGIN)
	public String serveLogin(Model model) {
		model.addAttribute("event", eventRepository.getLatestEvent());
		model.addAttribute("account", new Account());
		model.addAttribute("authentication", "login");
		return Path.Template.INDEX;
	}

	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		Account account = new Account();
		modelAndView.addObject("account", account);
		modelAndView.setViewName("registration");
		return modelAndView;
	}


	@PostMapping(Path.Web.REGISTRATION)
	public RedirectView handleRegistration(Model model,
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
		return new RedirectView("/");
	}

	@GetMapping(Path.Web.LOGOUT)
	public String handleLogOut(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return Path.Template.INDEX;
	}

}