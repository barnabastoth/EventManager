package application.repository;

import application.model.User.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}
