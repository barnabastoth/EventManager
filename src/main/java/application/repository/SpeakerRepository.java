package application.repository;

import application.model.Event.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    Speaker findOne(Long id);
}
