package application.service;

import application.model.Account.Account;
import application.model.Account.Role;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
		account.setImgPath("/images/user.png");
        account.setActive(1);
        account.setMemberSince(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        Role userRole = roleRepository.findByRole("USER");
        account.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepository.save(account);
	}

	public void saveAdmin(Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		account.setActive(1);
		account.setImgPath("/images/user.png");
		account.setMemberSince(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		Role userRole2 = roleRepository.findByRole("ADMIN");
		account.setRoles(new HashSet<>(Arrays.asList(userRole2)));
		userRepository.save(account);
	}

	public void saveOwner(Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		account.setActive(1);
		account.setImgPath("/images/user.png");
		account.setMemberSince(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		Role userRole2 = roleRepository.findByRole("ADMIN");
		Role userRole3 = roleRepository.findByRole("OWNER");
		account.setRoles(new HashSet<>(Arrays.asList(userRole2, userRole3)));
		userRepository.save(account);
	}

	public Set<Account> getSpeakersByEmail(Set<String> emails) {
		Set<Account> speakers = new HashSet<>();
		for (String email : emails) {
			speakers.add(userRepository.findByEmail(email));
		}
		return speakers;
	}

}
