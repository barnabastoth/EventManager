package application.model.Menu;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Menu.getMenuByID",
                query = "SELECT o FROM Menu o WHERE id = :id")
})
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long ID;

    private String title;
    @Lob
    private String description;

    public Menu() {
    }

    public Menu(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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
}
