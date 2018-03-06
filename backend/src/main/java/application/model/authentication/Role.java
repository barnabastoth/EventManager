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
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Role(String role) {
		this.role = role;
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
