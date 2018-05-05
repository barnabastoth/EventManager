package application.model.event;

import application.model.event.Event;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "Field")
public class Field {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob @Type(type = "text")
    private String text;
    private String subText;

    private String icon;

    private Integer active;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Field() {
    }

    public Field(String text, String subText, String icon, Integer active, Event event) {
        this.text = text;
        this.subText = subText;
        this.icon = icon;
        this.active = active;
        this.event = event;
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

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
