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
		
			createRoleIfNotFound("ADMIN", "Admin Role");
			createRoleIfNotFound("MANAGER", "Manager Role");
			createRoleIfNotFound("USER", "User Role");
			
			
			User garnold = new User("garnold", "edm4life", true);
			garnold.addRole(rrepository.findByName("ADMIN"));
			garnold.addRole(rrepository.findByName("USER"));
			User dmccleary = new User("dmccleary", "php4good", true);
			dmccleary.addRole(rrepository.findByName("USER"));
			User rfulcher = new User("rfulcher", "drupal#1", true);
			rfulcher.addRole(rrepository.findByName("MANAGER"));
			urepository.save(garnold);
			urepository.save(dmccleary);
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
