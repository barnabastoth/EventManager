package application.model.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewEditUser {
    private String id;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String oldPassword;
    private String newPassword1;
    private String newPassword2;
    private String description;
    private String profession;
}
