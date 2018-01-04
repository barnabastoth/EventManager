package application.model.Event;

import application.model.User.Account;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@NamedQueries({
        @NamedQuery(name = "Event.getLatestEvent", query = "SELECT e FROM Event e WHERE id = (SELECT max(id) FROM Event)")
})
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    public long id;

    public String title;
    public String name;

    private String locationByPublicTransport;
    private String locationByCar;
    private String address;
    private String mapLatitude;
    private String mapLongTitude;

    public String date;

    @Lob
    @Type(type = "text")
    private String description;

    private String price;
    private String duration;
    private String imgPath;
    private String ticketLink;

    @ManyToMany()
    @JoinTable(
            name = "event_speakers",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<Account> speakers = new HashSet<>();

    public Event() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.date = dateFormat.format(date);
    }

    public Event(String title, String name, String locationByPublicTransport, String locationByCar, String address, String mapLatitude, String mapLongTitude, String date, String description, String price, String duration, String imgPath, String ticketLink) {
        this();
        this.title = title;
        this.name = name;
        this.locationByPublicTransport = locationByPublicTransport;
        this.locationByCar = locationByCar;
        this.address = address;
        this.mapLatitude = mapLatitude;
        this.mapLongTitude = mapLongTitude;
        this.date = date;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.imgPath = imgPath;
        this.ticketLink = ticketLink;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setLocationByPublicTransport(String locationByPublicTransport) {
        this.locationByPublicTransport = locationByPublicTransport;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
