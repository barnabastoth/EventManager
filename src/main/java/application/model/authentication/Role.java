package application.model.authentication;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="role_id") private Long id;
	@Column(name="role") private String role;

	@OneToMany(mappedBy = "role")
	@JsonBackReference
	private Set<User> users = new HashSet<>();

	public Role() { }

	public Role(String role) {
		this.role = role;
	}
}
