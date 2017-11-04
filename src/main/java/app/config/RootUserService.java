// package app.config;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.beans.factory.annotation.Autowired;
// import java.util.ArrayList;
// import java.util.List;

// import app.models.Customer;
// import app.services.CustomerManager;
// import org.springframework.stereotype.Service;


// @Service
// public class RootUserService implements UserDetailsService {

// @Autowired	
// private CustomerManager customerManager;
	
// 	public RootUserService() {
// 	}
	
// 	public RootUserService(CustomerManager customerManager) {
// 	this.customerManager = customerManager;
// 	}

// 	@Override
// 	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
// 		Customer root = customerManager.getByEmail(email);
// 		if (root != null) {
// 			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

// 			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
// 			return new User(root.getEmail(),root.getPassword(),authorities);
// 		}throw new UsernameNotFoundException("User '" + email + "' not found.");
// 	}
// }