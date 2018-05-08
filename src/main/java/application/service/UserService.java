package application.service;


import application.model.authentication.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void deleteById(Long id);
    User findByUsername(String username);
    Optional<User> findById(Long id);
    User findByEmail(String email);

    User getLatestUser();
}
