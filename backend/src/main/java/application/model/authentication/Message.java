package application.model.authentication;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @GeneratedValue
    private long id;

    @Size(min = 4, max = 20, message = "Az üzenetnek legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String message;
    private LocalDateTime date;

    @ManyToOne @JoinColumn(name = "user_id") @JsonBackReference private User sender;
    @ManyToOne @JoinColumn(name = "user_id") @JsonBackReference private User receiver;

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
