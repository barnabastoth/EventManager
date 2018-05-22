package application.model.system;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewContactMessage {
    private String email;
    private String message;
    private String topic;
    private String userId;
    private Boolean isRead;

}
