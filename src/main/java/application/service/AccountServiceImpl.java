package application.service;

import application.model.account.Account;
import application.model.account.Role;
import application.repository.RoleRepository;
import application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class AccountServiceImpl implements AccountService {

	@Qualifier("accountRepository")
	@Autowired
	private AccountRepository accountRepository;
	@Qualifier("roleRepository")
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Account findUserByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

	@Override
	public void saveUser(Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setActive(1);
        account.setMemberSince(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        Role userRole = roleRepository.findByRole("USER");
        account.setRoles(new HashSet<>(Arrays.asList(userRole)));
		accountRepository.saveAndFlush(account);
	}

	@Override
	public void saveOwner(Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		account.setActive(1);
		account.setMemberSince(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		Role userRole2 = roleRepository.findByRole("ADMIN");
		Role userRole3 = roleRepository.findByRole("OWNER");
		account.setRoles(new HashSet<>(Arrays.asList(userRole2, userRole3)));
		accountRepository.saveAndFlush(account);
	}

}
