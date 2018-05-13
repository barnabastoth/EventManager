package application.repository;

import application.model.event.EventField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventFieldRepository extends JpaRepository<EventField, Long> {

}
