package application.repository;

import application.model.event.Event;
import application.model.event.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

//
//    @Query("select new application.model.event.Speaker(s.id, s.name, s.lastName, s.username, s.description) from user")
//    Speaker getSpeaker(@Param("id") Long id);
}
