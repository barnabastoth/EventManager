package application.repository;

import application.model.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event getLatestEvent();
    Event findOne(Long id);
}
