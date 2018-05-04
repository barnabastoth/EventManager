package application.model.event;

import application.model.Field;
import application.model.authentication.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@NamedQueries({
        @NamedQuery(name = "Event.getLatestEvent", query = "SELECT e FROM Event e WHERE id = (SELECT max(id) FROM Event)")
})
@Entity
@Table(name = "event")
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "event_id")
    private long id;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field title;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field name;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field locationByPublicTransport;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field locationByCar;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field address;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field mapLatitude;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field mapLongTitude;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field description;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field price;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field duration;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event") private Field ticketLink;
    private LocalDateTime date;
    private Boolean active;

    @JsonBackReference
    @ManyToMany(mappedBy = "events")
    private Set<User> speakers = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true) @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "events")
    private Set<User> users = new HashSet<>();

    public Event() { }

    public long getId() {
        return id;
    }

    public Field getTitle() {
        return title;
    }

    public void setTitle(Field title) {
        this.title = title;
    }

    public Field getName() {
        return name;
    }

    public void setName(Field name) {
        this.name = name;
    }

    public Field getLocationByPublicTransport() {
        return locationByPublicTransport;
    }

    public void setLocationByPublicTransport(Field locationByPublicTransport) {
        this.locationByPublicTransport = locationByPublicTransport;
    }

    public Field getLocationByCar() {
        return locationByCar;
    }

    public void setLocationByCar(Field locationByCar) {
        this.locationByCar = locationByCar;
    }

    public Field getAddress() {
        return address;
    }

    public void setAddress(Field address) {
        this.address = address;
    }

    public Field getMapLatitude() {
        return mapLatitude;
    }

    public void setMapLatitude(Field mapLatitude) {
        this.mapLatitude = mapLatitude;
    }

    public Field getMapLongTitude() {
        return mapLongTitude;
    }

    public void setMapLongTitude(Field mapLongTitude) {
        this.mapLongTitude = mapLongTitude;
    }

    public Field getDescription() {
        return description;
    }

    public void setDescription(Field description) {
        this.description = description;
    }

    public Field getPrice() {
        return price;
    }

    public void setPrice(Field price) {
        this.price = price;
    }

    public Field getDuration() {
        return duration;
    }

    public void setDuration(Field duration) {
        this.duration = duration;
    }

    public Field getTicketLink() {
        return ticketLink;
    }

    public void setTicketLink(Field ticketLink) {
        this.ticketLink = ticketLink;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<User> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<User> speakers) {
        this.speakers = speakers;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
