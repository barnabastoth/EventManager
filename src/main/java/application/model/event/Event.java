package application.model.event;

import application.model.account.Account;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@NamedQueries({
        @NamedQuery(name = "Event.getLatestEvent", query = "SELECT e FROM Event e WHERE id = (SELECT max(id) FROM Event)")
})
@Entity
@Table(name = "event")
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "event_id") public long id;
    public String title;
    public String name;
    private String locationByPublicTransport;
    private String locationByCar;
    private String address;
    private String mapLatitude;
    private String mapLongTitude;
    public LocalDateTime date;
    @Lob @Type(type = "text") private String description;
    private String price;
    private String duration;
    private String ticketLink;
    private Integer active;

    @ElementCollection
    private Set<Account> speakers = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(mappedBy = "events")
    private Set<Account> accounts = new HashSet<>();

    public Event() {
    }

    public Event(String title, String name, String locationByPublicTransport, String locationByCar, String address, String mapLatitude, String mapLongTitude, String date, String description, String price, String duration, String ticketLink) {
        this();
        this.title = title;
        this.name = name;
        this.locationByPublicTransport = locationByPublicTransport;
        this.locationByCar = locationByCar;
        this.address = address;
        this.mapLatitude = mapLatitude;
        this.mapLongTitude = mapLongTitude;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.ticketLink = ticketLink;
    }

    public Boolean hasAccount(Account account) {
        for (Account acc : accounts) {
            if(acc.getId() == account.getId()) {
                return false;
            }
        }
        return true;
    }

    public Integer getActive() { return active; }
    public void setActive(Integer active) { this.active = active; }
    public Set<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
    public Set<Account> getAttendees() { return accounts; }
    public void setAttendees(Set<Account> accounts) { this.accounts = accounts; }
    public Set<Comment> getComments() { return comments; }
    public void setComments(Set<Comment> comments) { this.comments = comments; }
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocationByPublicTransport() {
        return locationByPublicTransport;
    }
    public void setLocationByPublicTransport(String locationByPublicTransport) { this.locationByPublicTransport = locationByPublicTransport; }
    public String getLocationByCar() {
        return locationByCar;
    }
    public void setLocationByCar(String locationByCar) {
        this.locationByCar = locationByCar;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMapLatitude() {
        return mapLatitude;
    }
    public void setMapLatitude(String mapLatitude) {
        this.mapLatitude = mapLatitude;
    }
    public String getMapLongTitude() {
        return mapLongTitude;
    }
    public void setMapLongTitude(String mapLongTitude) {
        this.mapLongTitude = mapLongTitude;
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getTicketLink() {
        return ticketLink;
    }
    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
    }
    public Set<Account> getSpeakers() { return speakers; }
    public void setSpeakers(Set<Account> speakers) { this.speakers = speakers; }
}
