package edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edm.infrastructure.AccountRepository;
import edm.infrastructure.UserRepository;
import edm.model.Account;
import edm.model.User;

@SpringBootApplication
public class SBApplication {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SBApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(AccountRepository arepository, UserRepository urepository) {
		return args -> {
			Account checking = new Account("Checking", "GPA");
			Account savings = new Account("Savings", "GA");
			arepository.save(checking);
			arepository.save(savings);
		
			User garnold = new User("garnold", "edm4life", "ADMIN");
			User dmccleary = new User("dmccleary", "php4good", "USER");
			User rfulcher = new User("rfulcher", "drupal#1", "USER");
			urepository.save(garnold);
			urepository.save(dmccleary);
			urepository.save(rfulcher);
			
		arepository.findAll().forEach(System.out::println);
		urepository.findAll().forEach(System.out::println);
		};
	}
}
