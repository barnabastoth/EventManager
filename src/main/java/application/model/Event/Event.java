package application.model.Event;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;


@NamedQueries({
        @NamedQuery(name = "Event.getLatestEvent", query = "SELECT e FROM Event e WHERE id = (SELECT max(id) FROM Event)")
})
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String title;
    public String name;

    private String locationByPublicTransport;
    private String locationByCar;
    private String address;
    private Double mapLatitude;
    private Double mapLongtitude;

    public Date date;

    @Lob
    @Type(type = "text")
    private String description;

    private Long price;
    private Long duration;
    private String imgPath;
    private String ticketLink;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event", cascade = CascadeType.ALL)
    private List<Speaker> speakers = new ArrayList<>();


    public Event() {
    }

    public Event(String title, String name, String locationByPublicTransport, String locationByCar, String address, Double mapLatitude, Double mapLongtitude, Date date, String description, Long price, Long duration, String imgPath, String ticketLink) {
        this.title = title;
        this.name = name;
        this.locationByPublicTransport = locationByPublicTransport;
        this.locationByCar = locationByCar;
        this.address = address;
        this.mapLatitude = mapLatitude;
        this.mapLongtitude = mapLongtitude;
        this.date = date;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.imgPath = imgPath;
        this.ticketLink = ticketLink;
    }

    public void addSpeaker(Speaker speaker) {
        speakers.add(speaker);
        speaker.setEvent(this);
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

    public Double getMapLatitude() {
        return mapLatitude;
    }

    public void setMapLatitude(Double mapLatitude) {
        this.mapLatitude = mapLatitude;
    }

    public Double getMapLongtitude() {
        return mapLongtitude;
    }

    public void setMapLongtitude(Double mapLongtitude) {
        this.mapLongtitude = mapLongtitude;
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

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", locationByPublicTransport='" + locationByPublicTransport + '\'' +
                ", locationByCar='" + locationByCar + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", imgPath='" + imgPath + '\'' +
                ", ticketLink='" + ticketLink + '\'' +
                ",  \n speakers=" + speakers +
                '}';
    }
}
