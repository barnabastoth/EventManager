package application.utils;

import application.model.authentication.Role;
import application.model.authentication.User;
import application.repository.RoleRepository;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class AuthenticationUtils {
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private UserService userService;

    public void registerNewUser(String userName, String email, String password) {
        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setActive(1);
        user.setMemberSince(LocalDateTime.now());
        userService.save(user);
    }
}
