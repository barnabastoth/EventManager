package application.model.Account;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="role_id") private int id;
	@Column(name="role") private String role;
	@ManyToMany(mappedBy = "roles") private Set<Account> accounts = new HashSet<>();


	public Role() {
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	public Role(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
