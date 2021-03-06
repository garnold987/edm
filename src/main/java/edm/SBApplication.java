package edm;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edm.infrastructure.AccountRepository;
import edm.infrastructure.user.RoleRepository;
import edm.infrastructure.user.UserRepository;
import edm.model.Account;
import edm.model.user.Role;
import edm.model.user.User;

@SpringBootApplication
public class SBApplication {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SBApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(AccountRepository arepository, UserRepository urepository, RoleRepository rrepository) {
		return args -> {
			Account checking = new Account("Checking", "GPA");
			Account savings = new Account("Savings", "GA");
			arepository.save(checking);
			arepository.save(savings);
		
			createRoleIfNotFound("ROLE_ADMIN", "Admin Role");
			createRoleIfNotFound("ROLE_MANAGER", "Manager Role");
			createRoleIfNotFound("ROLE_USER", "User Role");
			
			
			User garnold = new User("Greg", "garnold", "garnold@test.com", "edm4life", true);
			urepository.save(garnold);
			garnold.addRole(rrepository.findByName("ROLE_ADMIN"));
			garnold.addRole(rrepository.findByName("ROLE_USER"));
			urepository.save(garnold);
			User dmccleary = new User("Dan", "dmccleary", "dan@test.com", "php4good", true);
			urepository.save(dmccleary);
			dmccleary.addRole(rrepository.findByName("ROLE_USER"));
			urepository.save(dmccleary);
			User rfulcher = new User("Robert", "rfulcher", "robby@test.com", "drupal#1", true);
			urepository.save(rfulcher);
			rfulcher.addRole(rrepository.findByName("ROLE_MANAGER"));
			urepository.save(rfulcher);
			
		arepository.findAll().forEach(System.out::println);
		urepository.findAll().forEach(System.out::println);
		};
	}
	
	@Transactional
	private Role createRoleIfNotFound(String name, String description) {
		Role role = roleRepository.findByName(name);
		if(role == null) {
			role = new Role(name, description);
			roleRepository.save(role);
		}
		return role;
	}
}
