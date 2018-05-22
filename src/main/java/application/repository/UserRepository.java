package application.repository;

import application.model.authentication.EditUser;
import application.model.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findByEmail(String email);

//    @Query("select new application.model.event.Speaker(u.id, u.name, u.lastName, u.username, u.description) from User u")
//    Speaker getSpeakers(@Param("id") Long id);

    @Query("select new application.model.authentication.EditUser(u.id, u.name, u.lastName, u.username, u.email, u.profession, u.description) FROM User u where u.username = :username")
    EditUser getEditUserByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("delete from User u where u.username = :username")
    void deleteByUsername(@Param("username") String username);
}
