package application.model.event;

import application.model.authentication.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "event_id")
    private Long id;
    private String name;
    private String address;
    private LocalDateTime date;
    private String active;
    private byte[] image;
    @Column(length = 1024) private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private List<EventField> fields = new ArrayList<>();

    @ElementCollection
    private Map<String, String> settings = new HashMap<>();

    @JsonManagedReference
    @ManyToMany()
    @JoinTable(name = "user_speakers"
            , joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "speakers")
    private Set<User> speakers = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();

    @JsonManagedReference
    @ManyToMany()
    @JoinTable(name = "event_attendees"
            , joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<User> attendees = new HashSet<>();

}
