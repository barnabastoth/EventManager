package application.model.Event;


import application.model.Account.Account;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;
    private String message;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
