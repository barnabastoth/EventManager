package application.model.event;

import application.model.authentication.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@NamedQueries({
        @NamedQuery(name = "Event.getLatestEvent", query = "SELECT e FROM Event e WHERE id = (SELECT max(id) FROM Event)")
})
@Entity
@Table(name = "event")
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "event_id")
    private long id;

//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field title;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field name;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field locationByPublicTransport;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field locationByCar;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field address;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field mapLatitude;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field mapLongTitude;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field description;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field price;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field duration;
//    @JsonManagedReference @OneToOne(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval = true) private Field ticketLink;
    @JsonManagedReference @OneToMany(mappedBy = "event", cascade = CascadeType.ALL ,fetch = FetchType.EAGER) private Map<String, Field> fields = new HashMap<>();
    private LocalDateTime date;
    private Integer active;

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

    public Map<String, Field> getFields() {
        return fields;
    }

    public void setFields(Map<String, Field> fields) {
        this.fields = fields;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
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
