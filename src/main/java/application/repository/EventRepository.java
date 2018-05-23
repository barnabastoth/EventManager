package application.repository;

import application.model.authentication.User;
import application.model.event.Event;
import application.model.event.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from Event e where e.active = '1' AND e.id = ?1")
    Optional<Event> getActiveEventById(@Param("id") Long id);

    @Query("select e from Event e order by e.date desc")
    List<Event> findAllByDate();

    @Query("select e from Event e where e.active = '1' order by e.date desc")
    List<Event> findAllActiveByDate();

}
