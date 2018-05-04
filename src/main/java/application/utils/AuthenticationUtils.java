package application.utils;

import application.model.authentication.RegisterUser;
import application.model.authentication.Role;
import application.model.authentication.User;
import application.repository.RoleRepository;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthenticationUtils {
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private UserService userService;
    @Autowired private RoleRepository roleRepository;

    public void registerNewUser(RegisterUser registerUser) {
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setName(registerUser.getName());
        user.setLastName(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
        user.setActive(true);
        user.setMemberSince(LocalDateTime.now());
        user.getRoles().add(roleRepository.findByRole("Felhasználó"));
        userService.save(user);
    }
}
