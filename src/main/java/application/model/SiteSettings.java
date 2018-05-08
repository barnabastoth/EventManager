package application.model;

import javax.persistence.*;

@Entity
@Table(name = "SiteSettings")
public class SiteSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean leftBarOpen;
    private Boolean rightBarOpen;
    private Boolean eventBarOpen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
