package application.repository;

import application.model.authentication.User;
import application.model.authentication.UserNecessaryFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findLatestUser();

//    @Query("select new application.model.authentication.UserNecessaryFields(u.username, u.profession, count (e.id)) " +
//            "from User u join u.events e where u.username= :un")
//    UserNecessaryFields findUNFByUsername(@Param("un") String un);
//    LOMBOK
}
