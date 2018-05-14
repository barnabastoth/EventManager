package application.utils;

import application.model.authentication.RegisterUser;
import application.model.authentication.Role;
import application.model.authentication.User;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AuthenticationUtils {
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private UserService userService;
    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;

    public void registerNewUser(RegisterUser registerUser) {
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setName(registerUser.getName());
        user.setLastName(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
        user.setActive(1);
        user.setMemberSince(LocalDateTime.now());
        user.setRole(roleRepository.findByRole("Felhasználó"));
        userService.save(user);
    }

    public Boolean isUserAnAdminOrOwner(Principal principal) {
        if(principal != null) {
            Optional<User> user = userRepository.findByUsername(principal.getName());
            if(user.isPresent()) {
                return user.get().getRole().getRole().equals("Tulajdonos") || user.get().getRole().getRole().equals("Admin");
            }
        }
        return false;
    }
}
