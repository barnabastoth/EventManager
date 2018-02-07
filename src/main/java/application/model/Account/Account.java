package application.model.Account;

import application.model.Event.Comment;
import application.model.Event.Event;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "account")
public class Account {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "user_id") private int id;
	@Column(name = "username") @Length(min = 3, message = "A becenevednek legalább 3 betüből kell állnia") @NotEmpty private String userName;
	@Column(name = "imgPath") @Length(min = 3, message = "A képednek legalább 3 betüből kell állnia") private String imgPath;
	@Column(name = "email") @Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") private String email;
	@Column(name = "password") @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") @Transient private String password;
	@Column(name = "name") @NotEmpty(message = "*Please provide your name") private String name;
	@Column(name = "last_name") @NotEmpty(message = "*Please provide your last name") private String lastName;
	@Column(name = "profession") private String profession;
	@Column(name = "website") private String website;
	@Column(name = "description", columnDefinition="text") private String description;
	@Column(name = "active") private int active;
	@Column(name = "memberSince") private LocalDateTime memberSince;

	@ManyToMany()
	@JoinTable(name = "user_role"
			, joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "user_events"
			, joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "event_id")
	)
	private Set<Event> events = new HashSet<>();

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

	public void addEvent(Event event) {
		events.add(event);
	}



	public Set<Event> getEvents() { return events; }

	public void setEvents(Set<Event> events) { this.events = events; }

	public int getId() { return id; }

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

	public String getUserName() { return userName; }

	public void setUserName(String userName) { this.userName = userName; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public LocalDateTime getMemberSince() { return memberSince; }

	public void setMemberSince(LocalDateTime memberSince) { this.memberSince = memberSince; }

	public String getImgPath() { return imgPath; }

	public void setImgPath(String imgPath) { this.imgPath = imgPath; }
}
