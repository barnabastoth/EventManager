package application.model.system;


import application.model.authentication.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "contactMessage")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    @ManyToOne()
    @JoinColumn(name = "user.id")
    @JsonBackReference
    private User sender;

    private String message;

    public ContactMessage() { }

    public ContactMessage(String email, User sender, String message) {
        this.email = email;
        this.sender = sender;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
