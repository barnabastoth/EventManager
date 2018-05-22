package application.model.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUser {
    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String profession;
    private String description;

    public EditUser(Long id, String name, String lastName, String username, String email, String profession, String description) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.profession = profession;
        this.description = description;
    }
}
