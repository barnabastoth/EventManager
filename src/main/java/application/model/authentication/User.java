package application.model.authentication;

import application.model.event.Comment;
import application.model.event.Event;
import application.model.system.ContactMessage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true)
    @Size(min = 4, max = 10, message = "A felhasználónevednek legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String username;

    @Column(name = "email", unique = true)
    @NotEmpty(message = "Az email címed nem lehet üres.")
    @Email(message = "Az email címed nem tűnik igazinak.")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    @Size(min = 3, max = 10, message = "A keresztnevednek legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String name;

    @Column(name = "last_name")
    @Size(min = 3, max = 10, message = "A családnevednek legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String lastName;

    @Column(name = "profession")
    @Size(min = 6, max = 40, message = "A foglalkozásodnak legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String profession;

    @Column(name = "website")
    @Size(min = 6, max = 100, message = "A weboldalad címének legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String website;

    @Column(name = "description", columnDefinition="text")
    @Size(min = 20, max = 420, message = "A bemutatkozódnak legalább {min} és legfeljebb {max} karakterből kell állnia.")
    private String description;

    @Column(name = "active")
    private Integer active;

    @Column(name = "memberSince")
    private LocalDateTime memberSince;

    @Column(name = "image")
    private byte[] image;

    @ManyToOne @JoinColumn(name = "role_id")
    @JsonManagedReference
    private Role role;

    @ManyToMany(mappedBy = "attendees")
    @JsonBackReference
    private Set<Event> attendedEvents = new HashSet<>();

    @ManyToMany(mappedBy = "speakers")
    @JsonBackReference
    private Set<Event> speakerAt = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    @NotFound(action= NotFoundAction.IGNORE)
    @OrderBy("id")
    private Set<ContactMessage> contactMessages = new HashSet<>();

}


