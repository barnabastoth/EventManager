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
    private Long id;
    private String name;
    private String address;
    private LocalDateTime date;
    @Column(length = 500) private String description;

    private Integer active;

    @JsonManagedReference
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private List<Field> fields = new ArrayList<>();

    @ElementCollection
    private Map<String, String> settings = new HashMap<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "events")
    private Set<User> speakers = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "events")
    private Set<User> users = new HashSet<>();

    public Event() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
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
