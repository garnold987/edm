package edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edm.infrastructure.AccountRepository;

@SpringBootApplication
public class SBApplication {

	@Autowired
	AccountRepository accountRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SBApplication.class, args);
	}
}
