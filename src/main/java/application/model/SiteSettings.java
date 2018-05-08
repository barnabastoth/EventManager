package application.model;

import javax.persistence.*;

@Entity
@Table(name = "SiteSettings")
public class SiteSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String welcomeMessage;
    private Boolean leftBarOpen;
    private Boolean rightBarOpen;
    private Boolean eventBarOpen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public Boolean getLeftBarOpen() {
        return leftBarOpen;
    }

    public void setLeftBarOpen(Boolean leftBarOpen) {
        this.leftBarOpen = leftBarOpen;
    }

    public Boolean getRightBarOpen() {
        return rightBarOpen;
    }

    public void setRightBarOpen(Boolean rightBarOpen) {
        this.rightBarOpen = rightBarOpen;
    }

    public Boolean getEventBarOpen() {
        return eventBarOpen;
    }

    public void setEventBarOpen(Boolean eventBarOpen) {
        this.eventBarOpen = eventBarOpen;
    }
}
