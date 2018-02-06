package application.service;


import application.model.Account.Account;

public interface UserService {
	Account findUserByEmail(String email);
	void saveUser(Account account);
}
