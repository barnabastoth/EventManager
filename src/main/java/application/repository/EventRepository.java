package application.repository;

import application.model.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

public interface EventRepository extends JpaRepository<Event, Long> {

//    Event getEventById(Long id);
    Event getLatestEvent();
    Event findOne(Long id);
}
//    @Query (value = "UPDATE vacancy SET salary=?2, location=?3,functionality=?4, description=?5 WHERE id = ?1  ", nativeQuery = true)
//    @Query(value = "SELECT e FROM Event e WHERE e.ID = :id")