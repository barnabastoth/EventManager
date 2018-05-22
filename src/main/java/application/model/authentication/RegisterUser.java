package application.model.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterUser {

    @Size(min = 4, max = 10, message = "A felhasználónevednek legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String username;

    @Size(min = 6, max = 20, message = "A jelszavadnak minimum {min} és maximum {max} karakterből kell állnia.")
    private String password;

    @Size(min = 3, max = 10, message = "A becenevednek minimum {min} és maximum {max} karakterből kell állnia.")
    private String name;

    @Size(min = 3, max = 10, message = "A keresztnevednek minimum {min} és maximum {max} karakterből kell állnia.")
    private String lastName;

    @NotEmpty(message = "Az email címed nem lehet üres.")
    @Email(message = "Az email címed nem tűnik igazinak.")
    private String email;

}
