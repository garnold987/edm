package edm.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "roles")
	private String[] roles;
	
	protected User() { }
	
	public User(String username, String password, String... roles) {
		setUsername(username);
		setPassword(password);
		setRoles(roles);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	public boolean validatePassword(String password) {
		return new BCryptPasswordEncoder().matches(password, this.password);
	}
	
	@Override
	public String toString() {
		return String.format("User[id=%d, username='%s', password='%s', roles='%s']", 
									getId(), getUsername(), getPassword(), Arrays.toString(getRoles()));
	}
}
