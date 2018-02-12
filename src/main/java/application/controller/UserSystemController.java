package application.controller;

import application.model.Account.Account;
import application.repository.EventRepository;
import application.repository.UserRepository;
import application.service.UserService;
import application.utils.Path;
import application.utils.RequestUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

@Controller
public class UserSystemController {
	
	@Autowired private UserService userService;
	@Autowired private EventRepository eventRepository;
	@Autowired private RequestUtil requestUtil;
	@Qualifier("userRepository") @Autowired private UserRepository userRepository;
	@Autowired ServletContext servletContext;

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
	@GetMapping("/login")
	public String serveLoginPage(Model model) {
		model.addAttribute("event", eventRepository.getLatestEvent());
		model.addAttribute("account", new Account());
		model.addAttribute("authentication", "login");
        model.addAttribute("pageContent", Path.Fragment.EVENT);
		return "index";
	}

	@PreAuthorize("isAnonymous()")
	@GetMapping("/registration")
	public String serveRegistrationPage(Model model){
		model.addAttribute("event", eventRepository.getLatestEvent());
		model.addAttribute("account", new Account());
		model.addAttribute("authentication", "registration");
        model.addAttribute("pageContent", Path.Fragment.EVENT);
		return "index";
	}

	@PreAuthorize("isAnonymous()")
	@PostMapping("/registration")
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

	@GetMapping("/profile/{id}")
	public String serveProfilePage (Model model, @PathVariable("id") Long id) {
			Account account = userRepository.findById(id);
        model.addAttribute("pageContent", Path.Fragment.PROFILE);
		model.addAttribute("account", account);
		requestUtil.addProfileDetails(model, account);
		return "index";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/profile/{id}/edit")
	public String serveProfileEditPage(Model model, Authentication authentication, @PathVariable("id") Long id) {
		Account user = userService.findUserByEmail(authentication.getName());
		if(user.getId() == id || user.hasRole("ADMIN")) {
			Account account = userRepository.findById(id);
			model.addAttribute("account", account);
			model.addAttribute("pageContent", Path.Fragment.EDIT_PROFILE);
			return "index";
		}
		return "redirect:/";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/profile/{id}/edit")
	public String handleProfileEdit(@PathVariable("id") Long id, @ModelAttribute Account account, Authentication authentication) {
		Account loggedInUser = userService.findUserByEmail(authentication.getName());
		if(loggedInUser.getId().equals(id) || loggedInUser.hasRole("ADMIN")) {
			Account user = userRepository.findById(id);
			user.setEmail(account.getEmail());
			user.setName(account.getName());
			user.setLastName(account.getLastName());
			user.setWebsite(account.getWebsite());
			user.setProfession(account.getProfession());
			user.setDescription(account.getDescription());
			userRepository.saveAndFlush(user);
		}
		return "redirect:/profile/" + id;
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
	@GetMapping("/profile")
	public String serveAllProfile(Model model) {
		model.addAttribute("allUsers", userRepository.findAll());
		return Path.Fragment.ALL_USERS;
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/profile/{id}/uploadImg")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id, Model model) throws IOException {
		Account account = userRepository.findById(id);
		if (file.isEmpty()) {
			model.addAttribute("errorAlert", "A kép feltöltése sikertelen volt.");
			return "redirect:/profile/" + id;
		}
		try {
			byte[] bytes = file.getBytes();
			account.setImage(bytes);
			userRepository.saveAndFlush(account);
			model.addAttribute("successAlert", "A kép feltöltése sikeres volt.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/profile/" + id;
	}

	@GetMapping(value = "/profile/{id}/profilepic", produces = MediaType.IMAGE_JPEG_VALUE)
	public void serveProfileImage(@PathVariable("id") Long id, HttpServletResponse response)
			throws IOException{
		Account account = userRepository.findById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		if(account.getImage() != null) {
			response.getOutputStream().write(account.getImage());
		} else {
			ClassLoader classLoader = getClass().getClassLoader();
			File defaultProfilePic = new File(classLoader.getResource("public/images/user.png").getFile());
			BufferedImage originalImage = ImageIO.read(defaultProfilePic);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "png", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			byte[] xoredImage = new byte[imageInByte.length];
			response.getOutputStream().write(imageInByte);
		}
	}

}
