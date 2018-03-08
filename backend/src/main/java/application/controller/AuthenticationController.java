package application.controller;

import application.config.JwtTokenUtil;
import application.model.authentication.User;
import application.model.authentication.AuthToken;
import application.repository.RoleRepository;
import application.service.UserService;
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


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> login(@RequestParam("username") String userName, @RequestParam("password") String password) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userService.findByUsername(userName);
        if(user != null) {
            final String token = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new AuthToken(token));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> register(@RequestParam("username") String userName,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password) {

        User userByUsername = userService.findByUsername(userName);
        User userByEmail = userService.findByEmail(email);
        if(userByEmail == null && userByUsername == null) {
            User user = new User();
            user.setUsername(userName);
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setActive(1);
            user.setRoles(Stream.of(roleRepository.findByRole("USER")).collect(Collectors.toSet()));
            user.setMemberSince(LocalDateTime.now());
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
