package edm.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edm.configuration.security.UserPrincipal;
import edm.infrastructure.user.UserRepository;
import edm.model.user.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = userRepository.findByUsername(username);
		if(users == null || users.size() == 0) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return UserPrincipal.create(users.get(0));
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found with id : " + id)
		);
			
		return UserPrincipal.create(user);
	}
	
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
}
