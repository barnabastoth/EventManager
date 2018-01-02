package application.service;


import application.model.User.Account;

public interface UserService {
	Account findUserByEmail(String email);
	void saveUser(Account account);
}
