package application.model.authentication;

import application.model.event.Comment;
import application.model.event.Event;
import application.model.system.ContactMessage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role"
            , joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonManagedReference
    @Column(name = "roles")
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "attendees")
    @JsonBackReference
    private Set<Event> attendedEvents = new HashSet<>();

    @ManyToMany(mappedBy = "speakers") @JsonBackReference
    private Set<Event> speakerAt = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    @OrderBy("id")
    private Set<ContactMessage> contactMessages = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public LocalDateTime getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDateTime memberSince) {
        this.memberSince = memberSince;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Event> getAttendedEvents() {
        return attendedEvents;
    }

    public void setAttendedEvents(Set<Event> attendedEvents) {
        this.attendedEvents = attendedEvents;
    }

    public Set<Event> getSpeakerAt() {
        return speakerAt;
    }

    public void setSpeakerAt(Set<Event> speakerAt) {
        this.speakerAt = speakerAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<ContactMessage> getContactMessages() {
        return contactMessages;
    }

    public void setContactMessages(Set<ContactMessage> contactMessages) {
        this.contactMessages = contactMessages;
    }
}


