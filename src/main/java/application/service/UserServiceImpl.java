package application.service;

import application.model.Account.Account;
import application.model.Account.Role;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Qualifier("userRepository")
	@Autowired
	private UserRepository userRepository;
	@Qualifier("roleRepository")
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Account findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        account.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(account);
	}

	public void saveAdmin(Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		account.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		account.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(account);
	}

}
