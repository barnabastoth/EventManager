package application.service;


import application.model.authentication.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(Long id);
    User findByUsername(String username);
    User findById(Long id);
    User findByEmail(String email);

    User getLatestUser();
}
