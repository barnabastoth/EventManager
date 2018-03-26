package application.model.authentication;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class RegisterUser {

    @Size(min = 4, max = 20, message = "A felhasználónevednek legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String username;

    @Size(min = 6, max = 20, message = "A jelszavadnak minimum {min} és maximum {max} karakterből kell állnia.")
    private String password;

    @NotEmpty(message = "Az email címed nem lehet üres.")
    @Email(message = "Az email címed nem tűnik igazinak.")
    private String email;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email;
    }
}
