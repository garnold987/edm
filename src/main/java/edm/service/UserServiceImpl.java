package edm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edm.infrastructure.user.UserRepository;
import edm.model.user.Role;
import edm.model.user.User;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = userRepository.findByUsername(username);
		if(users == null || users.size() == 0) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(users.get(0).getUsername(), users.get(0).getPassword(), getGrantedAuthorities(users.get(0).getRoles()));
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName())); 
		}
		return authorities;
	}
	
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = findUserbyUsername(username);
//		
//		UserBuilder builder = null;
//		if (user != null) {
//			builder = org.springframework.security.core.userdetails.User.withUsername(username);
//			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
//			builder.roles(user.getRoles());
//		} 
//		else {
//			throw new UsernameNotFoundException("User '" + username + "' not found");
//		}
//		return builder.build();
//	}
//	
//	private User findUserbyUsername(String username) {
//		if(username.equalsIgnoreCase("admin")) {
//			return new User(username, "admin123", "ADMIN");
//		}
//		return null;
//			
//	}
}
