package edm.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edm.infrastructure.user.RoleRepository;
import edm.infrastructure.user.UserRepository;
import edm.model.user.Role;
import edm.model.user.User;
/**
 * https://medium.com/@coco.boudard/spring-boot-data-and-angular-ngrx-data-cbed33fc6015
 * @author Greg
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	public Iterable<User> users() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> user(@PathVariable long id) {
		return userRepository.findById(id);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/users")
	public User postUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/users/{id}")
	public User putUser(@PathVariable long id, @RequestBody User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	@GetMapping("/roles")
	public Iterable<Role> roles() {
		return roleRepository.findAll();
	}
	
	@GetMapping("/roles/{id}")
	public Optional<Role> role(@PathVariable long id) {
		return roleRepository.findById(id);
	}
	
}