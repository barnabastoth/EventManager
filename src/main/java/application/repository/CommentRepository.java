package application.repository;

import application.model.event.Comment;
import application.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c order by c.date desc")
    List<Comment> findAllByDateDesc();
}
