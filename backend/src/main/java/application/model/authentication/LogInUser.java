package application.model.authentication;

import javax.validation.constraints.Size;

public class LogInUser {

    @Size(min = 6, max = 20, message = "A jelszavadnak legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String username;

    @Size(min = 6, max = 20, message = "A jelszavadnak legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String password;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
