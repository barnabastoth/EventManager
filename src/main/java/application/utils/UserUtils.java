package application.utils;

import application.model.authentication.EditUserPojo;
import application.model.authentication.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUtils {

    @Autowired UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUserEdit(EditUserPojo editUserPojo) {
        Optional<User> user = userRepository.findById(Long.valueOf(editUserPojo.getId()));
        user.ifPresent(user1 -> {
            user1.setUsername(editUserPojo.getUsername());
            user1.setName(editUserPojo.getName());
            user1.setLastName(editUserPojo.getLastName());
            user1.setProfession(editUserPojo.getProfession());
            user1.setDescription(editUserPojo.getDescription());
            user1.setEmail(editUserPojo.getEmail());
            if (editUserPojo.getOldPassword1().equals(editUserPojo.getOldPassword2())) {
                user1.setPassword(bCryptPasswordEncoder.encode(editUserPojo.getNewPassword()));
            }
            userRepository.saveAndFlush(user1);
        });
    }

}
