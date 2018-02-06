package application.model.Event;


import javax.persistence.*;

@Entity
@Table(name = "eventField")
public class eventField {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "field_id")
    public long id;
    private String title;
    private boolean isDisabled;

    public eventField() {
    }

    public eventField(String title, boolean isDisabled) {
        this.title = title;
        this.isDisabled = isDisabled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
