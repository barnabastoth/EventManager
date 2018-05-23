package application.utils;

import application.model.authentication.NewEditUser;
import application.model.authentication.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUtils {

    @Autowired UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUserEdit(NewEditUser newEditUser) {
        Optional<User> user = userRepository.findById(Long.valueOf(newEditUser.getId()));
        user.ifPresent(user1 -> {
            user1.setUsername(newEditUser.getUsername());
            user1.setName(newEditUser.getName());
            user1.setLastName(newEditUser.getLastName());
            user1.setProfession(newEditUser.getProfession());
            user1.setDescription(newEditUser.getDescription());
            user1.setEmail(newEditUser.getEmail());
            if (newEditUser.getNewPassword1().equals(newEditUser.getNewPassword2()) &&
                    bCryptPasswordEncoder.encode(newEditUser.getOldPassword()).equals(user1.getPassword())) {
                user1.setPassword(bCryptPasswordEncoder.encode(newEditUser.getNewPassword1()));
            }
            userRepository.saveAndFlush(user1);
        });
    }

}
