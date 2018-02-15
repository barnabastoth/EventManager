package application.model.menu;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    long Id;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "description")
    @Type(type = "text")
    private String description;
    @Column(name = "route")
    private String route;

    public Menu() {
    }

    public Menu(String title, String description, String route) {
        this.title = title;
        this.description = description;
        this.route = route;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoute() { return route; }

    public void setRoute(String route) { this.route = route; }
}
