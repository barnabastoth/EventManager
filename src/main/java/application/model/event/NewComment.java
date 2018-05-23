package application.model.event;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;

@Getter
@Setter
public class NewComment {
    private Long eventId;
    private Long userId;
    @Lob
    @Type(type = "text")
    private String message;
}
