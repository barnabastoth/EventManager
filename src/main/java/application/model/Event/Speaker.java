package application.model.Event;


import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
//@Table
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne
    @JoinColumn(name = "event_id")
    public Event event;


    public String name;
    private String email;
    private String facebook;
    private String webPage;

    @Lob
    @Type(type = "text")
    private String description;
    private String imgPath;
    private String birthLocation;
    private String bornDate;

    public Speaker() {
    }

    public Speaker(String name, String email, String facebook, String webPage, String description, String imgPath, String birthLocation, String bornDate) {
        this.name = name;
        this.email = email;
        this.facebook = facebook;
        this.webPage = webPage;
        this.description = description;
        this.imgPath = imgPath;
        this.birthLocation = birthLocation;
        this.bornDate = bornDate;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getBirthLocation() {
        return birthLocation;
    }

    public void setBirthLocation(String birthLocation) {
        this.birthLocation = birthLocation;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }
}
