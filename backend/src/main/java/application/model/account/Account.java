package application.model.account;

import application.model.event.Comment;
import application.model.event.Event;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "account_id") private Long id;
	@Column(name = "email", unique = true) @Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") private String email;
	@Column(name = "password") @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") @Transient private String password;
	@Column(name = "name") @NotEmpty(message = "*Please provide your name") private String name;
	@Column(name = "last_name") @NotEmpty(message = "*Please provide your last name") private String lastName;
	@Column(name = "profession") private String profession;
	@Column(name = "website") private String website;
	@Column(name = "description", columnDefinition="text") private String description;
	@Column(name = "active") private int active;
	@Column(name = "memberSince") private LocalDateTime memberSince;
	@Column(name = "image") private byte[] image;

	@ManyToMany()
	@JoinTable(name = "account_role"
			, joinColumns = @JoinColumn(name = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "account_events"
			, joinColumns = @JoinColumn(name = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "event_id")
	)
	private Set<Event> events = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "account_speakers"
			, joinColumns = @JoinColumn(name = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "event_id")
	)
	private Set<Event> speakerAt = new HashSet<>();

	@OneToMany(
			mappedBy = "account",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Comment> comments = new ArrayList<>();


	public Account() {
	}

	public boolean hasRole(String role) {
		for (Role r : roles) {
			if(r.getRole().equals(role)) {
				return true;
			}
		}
		return false;
	}

	public Set<Event> getSpeakerAt() { return speakerAt; }
	public void setSpeakerAt(Set<Event> speakerAt) { this.speakerAt = speakerAt; }
	public void addEvent(Event event) {
		events.add(event);
	}
	public byte[] getImage() { return image; }
	public void setImage(byte[] image) { this.image = image; }
	public Set<Event> getEvents() { return events; }
	public void setEvents(Set<Event> events) { this.events = events; }
	public Long getId() { return id; }
	public String getWebsite() { return website; }
	public void setWebsite(String website) { this.website = website; }
	public String getProfession() { return profession; }
	public void setProfession(String profession) { this.profession = profession; }
	public List<Comment> getComments() { return comments; }
	public void setComments(List<Comment> comments) { this.comments = comments; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public int getActive() { return active; }
	public void setActive(int active) { this.active = active; }
	public Set<Role> getRoles() { return roles; }
	public void setRoles(Set<Role> roles) { this.roles = roles; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public LocalDateTime getMemberSince() { return memberSince; }
	public void setMemberSince(LocalDateTime memberSince) { this.memberSince = memberSince; }
}
