package application.model.authentication;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class RegisterUser {

    @Size(min = 5, message = "A felhasználónévnek legalább 5 karakter hosszunak kell lennie.") private String username;
    private String password;
    @NotEmpty(message = "Kérlek add meg az email cimedet") private String email;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email;
    }
}
