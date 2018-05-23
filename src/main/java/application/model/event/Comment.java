package application.model.event;

import application.model.authentication.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Comment")
public class Comment {

    @Id @Column @GeneratedValue(strategy = GenerationType.AUTO) public Long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Lob @Type(type = "text")
    private String message;

    private LocalDateTime date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
