package application.service;


import application.model.account.Account;

public interface AccountService {
	Account findUserByEmail(String email);
	void saveUser(Account account);
	void saveOwner(Account account);
}
