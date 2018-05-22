package application.model.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class LogInUser {

    @Size(min = 6, max = 20, message = "A jelszavadnak legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String username;

    @Size(min = 6, max = 20, message = "A jelszavadnak legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String password;

}
