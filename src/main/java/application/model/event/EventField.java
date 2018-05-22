package application.model.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "EventField")
public class EventField {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob @Type(type = "text")
    private String text;
    private String subText;

    private String icon;

    private Integer active;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
