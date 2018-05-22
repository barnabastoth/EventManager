package application.model.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
