package application.model.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewComment {
    private Long eventId;
    private Long userId;
    private String message;
}
