package application.repository;

import application.model.event.EventField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<EventField, Long> {

}
