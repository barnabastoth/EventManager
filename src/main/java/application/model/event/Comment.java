package application.model.event;

import application.model.authentication.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = "Comment.getLatestComment", query = "SELECT e FROM Comment e WHERE id = (SELECT max(id) FROM Comment)")
})
@Entity
@Table(name = "Comment")
public class Comment {

    @Id @Column @GeneratedValue(strategy = GenerationType.AUTO) public long id;
    @ManyToOne @JoinColumn(name = "user_id") @JsonBackReference private User user;
    private String message;
    private LocalDateTime date;
    @ManyToOne @JoinColumn(name = "event_id") @JsonBackReference private Event event;

    public long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
}