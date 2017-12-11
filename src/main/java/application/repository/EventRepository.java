package application.repository;

import application.model.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface EventRepository extends JpaRepository<Event, Long> {
    Event getLatestEvent();
    Event getEventByID(Long id);
}
