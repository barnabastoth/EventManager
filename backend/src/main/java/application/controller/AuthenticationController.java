package application.controller;

import application.config.JwtTokenUtil;
import application.model.authentication.AuthToken;
import application.model.authentication.Role;
import application.model.authentication.User;
import application.repository.RoleRepository;
import application.service.UserService;
import application.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> register(@RequestParam("username") String userName, @RequestParam("password") String password) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userService.findByUsername(userName);
        if(user != null) {
            List<Object> entities = new ArrayList<>();
            final String token = jwtTokenUtil.generateToken(user);
            entities.add(new AuthToken(token));
            entities.add(user);
            return new ResponseEntity<>(entities, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> register(@RequestParam("username") String userName,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password) {
        if(userService.findByUsername(userName) == null && userService.findByEmail(email) == null) {
            authenticationUtils.registerNewUser(userName, email, password);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("AASDASDSADSA", HttpStatus.CONFLICT);
    }

}
