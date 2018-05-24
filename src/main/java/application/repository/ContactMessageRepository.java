package application.repository;

import application.model.system.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    @Query("select count(c) FROM ContactMessage c where isRead = false ")
    Long getUnReadMessageCount();
}
