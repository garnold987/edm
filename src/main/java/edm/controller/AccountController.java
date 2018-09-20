package edm.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edm.infrastructure.AccountRepository;
import edm.model.Account;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AccountController {

	private AccountRepository accountRepository;
	
	public AccountController(AccountRepository aRepo) {
		this.accountRepository = aRepo;
	}
	
	@GetMapping("/accounts")
	public Iterable<Account> accounts() {
		return accountRepository.findAll();
	}
	
	@GetMapping("/accounts/{id}")
	public Optional<Account> account(@PathVariable long id) {
		return accountRepository.findById(id);
	}
	
	@DeleteMapping("/accounts/{id}")
	public void deleteAccount(@PathVariable long id) {
		accountRepository.deleteById(id);
	}
	
	@PostMapping("/accounts")
	public Account postAccount(@RequestBody Account account) {
		return accountRepository.save(account);
	}
	
	@PutMapping("/accounts/{id}")
	public Account putAccount(@PathVariable long id, @RequestBody Account account) {
		account.setId(id);
		return accountRepository.save(account);
	}
}
