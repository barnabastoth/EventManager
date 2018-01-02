package application.controller;

import application.model.User.Account;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes({"user"})
public class LoginController {
	
	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public String user() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = userService.findUserByEmail(auth.getName());
		if(account == null) {
			return "Vendég";
		}
		return account.getName();
	}

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		Account account = new Account();
		modelAndView.addObject("account", account);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid Account account, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Account accountExists = userService.findUserByEmail(account.getEmail());
		if (accountExists != null) {
			bindingResult
					.rejectValue("email", "error.account",
							"There is already a account registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(account);
			modelAndView.addObject("successMessage", "Account has been registered successfully");
			modelAndView.addObject("account", new Account());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + account.getName() + " " + account.getLastName() + " (" + account.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

}
