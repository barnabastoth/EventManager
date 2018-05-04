package application.model.event;

import application.model.event.Event;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "Field")
public class Field {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob @Type(type = "text")
    private String text;

    private Integer active;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Field(String text, Integer active) {
        this.text = text;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
