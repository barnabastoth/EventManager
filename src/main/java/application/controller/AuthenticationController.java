package application.controller;

import application.config.JwtTokenUtil;
import application.model.authentication.*;
import application.repository.RoleRepository;
import application.service.UserService;
import application.utils.AuthenticationUtils;
import application.utils.DataExtractionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenUtil jwtTokenUtil;
    @Autowired private UserService userService;
    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired RoleRepository roleRepository;
    @Autowired AuthenticationUtils authenticationUtils;
    @Autowired DataExtractionUtils dataExtractionUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInUser logInUser, BindingResult bindingResult) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        logInUser.getUsername(),
                        logInUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userService.findByUsername(logInUser.getUsername());
        if(user != null) {
            List<Object> entities = new ArrayList<>();
            final String token = jwtTokenUtil.generateToken(user);
            entities.add(new AuthToken(token));
            entities.add(user);
            return new ResponseEntity<>(entities, HttpStatus.OK);
        }
        return new ResponseEntity<>(dataExtractionUtils.extractErrors(bindingResult), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUser registerUser, BindingResult bindingResult) {
        if(userService.findByUsername(registerUser.getUsername()) == null && userService.findByEmail(registerUser.getEmail()) == null) {
            if(bindingResult.hasErrors()) {
                return new ResponseEntity<>(dataExtractionUtils.extractErrors(bindingResult), HttpStatus.CONFLICT);
            } else {
                authenticationUtils.registerNewUser(registerUser);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Ez a felhasználónév vagy email-cím már foglalt", HttpStatus.CONFLICT);
    }

    @PostMapping("/me")
    public ResponseEntity<?> resourceServer(@RequestBody String token) {
        System.out.println("TOKEN" + token);
        if(token != null) {
            User user = userService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
            String userName = user.getUsername();
            System.out.println("TOKENUSERNAME: " + userName);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
