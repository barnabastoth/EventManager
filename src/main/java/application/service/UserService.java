package application.service;


import application.model.Account.Account;

import java.util.Set;

public interface UserService {
	Account findUserByEmail(String email);
	void saveUser(Account account);
	Set<Account> getSpeakersByEmail(Set<String> emails);
}
