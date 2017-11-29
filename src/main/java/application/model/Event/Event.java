package application.model.Event;

import javax.persistence.*;
import java.util.*;


@NamedQueries({
        @NamedQuery(name = "Event.getEventByID",
                query = "SELECT o FROM Event o WHERE id = :id"),
        @NamedQuery(name = "Event.getLatestEvent",
                query = "SELECT o FROM Event o WHERE id = (SELECT max(id) FROM Event)")
})
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long ID;

    private String name;
    private String location;
    private Date date;
    private String description;
    private Long price;
    private Long duration;
    private String imgPath;
    private String ticketLink;


    public Event() {
    }

    public Event(String name, String location, Date date, String description, Long price, Long duration, String imgPath, String ticketLink) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.imgPath = imgPath;
        this.ticketLink = ticketLink;
    }


    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public Long getDuration() {
        return duration;
    }
    public void setDuration(Long duration) {
        this.duration = duration;
    }
    public String getImgPath() { return imgPath; }
    public void setImgPath(String imgPath) { this.imgPath = imgPath; }
    public String getTicketLink() { return ticketLink; }
    public void setTicketLink(String ticketLink) { this.ticketLink = ticketLink; }

}
