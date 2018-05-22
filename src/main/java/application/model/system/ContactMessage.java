package application.model.system;


import application.model.authentication.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "contactMessage")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    @ManyToOne()
    @JoinColumn(name = "user.id")
    @JsonManagedReference
    private User sender;

    private String topic;

    private String message;

    private boolean isRead;

    private LocalDateTime date;
}
