package edm.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	
	public final static String ROLE_USER = "USER";
	public final static String ROLE_MANAGER = "MANAGER";
	public final static String ROLE_ADMIN = "ADMIN";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column 
	private String description;
	
	protected Role() { }
	
	public Role(String name) {
		this.setName(name);
	}
	
	public Role(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Role[id=%d, name='%s', description='%s']", getId(), getName(), getDescription());
	}

}
