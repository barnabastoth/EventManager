package application.utils;

import application.model.authentication.EditUserPojo;
import application.model.authentication.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUtils {

    @Autowired UserRepository userRepository;

    public void saveUserEdit(EditUserPojo editUserPojo) {
        Optional<User> user = userRepository.findById(Long.valueOf(editUserPojo.getId()));
        user.ifPresent(user1 -> {
            user1.setUsername(editUserPojo.getUsername());
            user1.setName(editUserPojo.getName());
            user1.setLastName(editUserPojo.getLastName());
            user1.setProfession(editUserPojo.getProfession());
            user1.setDescription(editUserPojo.getDescription());
            user1.setEmail(editUserPojo.getEmail());
            userRepository.saveAndFlush(user1);
        });
    }

}
