package application.controller;

import application.model.User.Account;
import application.repository.EventRepository;
import application.repository.UserRepository;
import application.service.UserService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
			System.out.println("SUCSESS");
		}
		return new RedirectView("/");
	}

	@GetMapping(Path.Web.PROFILE)
	public String serveProfilePage (Model model, @PathVariable("id") int id) {
		Account account = userRepository.findById(id);
		model.addAttribute("account", account);
		requestUtil.addProfileDetails(model, account);
		return Path.Template.PROFILE;
	}

	@PostMapping(Path.Web.PROFILE)
	public RedirectView handleProfileEdit(@PathVariable("id") int id, @ModelAttribute Account account) {
		userRepository.save(account);
		return new RedirectView("/profile/" + Integer.toString(id));
	}



}
