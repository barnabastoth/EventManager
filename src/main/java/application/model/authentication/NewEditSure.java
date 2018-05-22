package application.model.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewEditSure {
    private String id;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String oldPassword1;
    private String oldPassword2;
    private String newPassword;
    private String description;
    private String profession;
}
