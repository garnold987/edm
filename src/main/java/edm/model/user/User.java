package edm.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@JsonAlias("realname")
	@Column(name = "name")
	private String name;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	private boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "users_roles", joinColumns = {
		@JoinColumn(name = "user_id") }, inverseJoinColumns = {
		@JoinColumn(name = "role_id") })
	private Collection<Role> roles = new ArrayList<Role>();
	
	protected User() { }
	
	public User(String name, String username, String email, String password, boolean enabled) {
		
		setName(name);
		setUsername(username);
		setEmail(email);
		setPassword(password);
		setEnabled(enabled);
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		getRoles().add(role);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, name='%s', username='%s', email='%s', password='%s', enabled=%s, roles='%s']", 
									getId(), getName(), getUsername(), getEmail(), getPassword(), isEnabled(), getRoles());
	}
}
