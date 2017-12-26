package application.service;

import application.model.User.Account;

public interface UserService {
    public Account findUserByEmail(String email);
    public void saveUser(Account user);
}
