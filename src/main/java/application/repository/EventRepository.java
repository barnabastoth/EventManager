package application.repository;

import application.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event getLatestEvent();
    Event findOne(Long id);
    List<Event> findAllByOrderByDateAsc();
}
