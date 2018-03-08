package application.model.authentication;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="role_id") private Long id;
	@Column(name="role") private String role;
	@ManyToMany(mappedBy = "roles") private Set<User> users = new HashSet<>();


	public Role() {
	}
	public Role(String role) {
		this.role = role;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Long getId() {
		return id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
