package edm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edm.infrastructure.user.UserRepository;
import edm.model.user.User;
/**
 * https://medium.com/@coco.boudard/spring-boot-data-and-angular-ngrx-data-cbed33fc6015
 * @author Greg
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("api/users")
	public Iterable<User> users() {
		userRepository.findAll();
	}
	
	
}
