package application.model.event;

public class Speaker {

    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String description;

    public Speaker(Long id, String name, String lastName, String username, String description) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
