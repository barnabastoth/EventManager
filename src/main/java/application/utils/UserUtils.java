package application.utils;

import application.model.authentication.NewEditSure;
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

    public void saveUserEdit(NewEditSure newEditSure) {
        Optional<User> user = userRepository.findById(Long.valueOf(newEditSure.getId()));
        user.ifPresent(user1 -> {
            user1.setUsername(newEditSure.getUsername());
            user1.setName(newEditSure.getName());
            user1.setLastName(newEditSure.getLastName());
            user1.setProfession(newEditSure.getProfession());
            user1.setDescription(newEditSure.getDescription());
            user1.setEmail(newEditSure.getEmail());
            if (newEditSure.getOldPassword1().equals(newEditSure.getOldPassword2())) {
                user1.setPassword(bCryptPasswordEncoder.encode(newEditSure.getNewPassword()));
            }
            userRepository.saveAndFlush(user1);
        });
    }

}
