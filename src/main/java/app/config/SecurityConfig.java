// package app.config;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
// import org.springframework.beans.factory.annotation.Autowired;
// import app.services.CustomerManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;


// @Configuration
// @EnableWebMvcSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
// 	@Autowired	
// 	private CustomerManager customerManager;

// 	@Override
// 	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
// 		auth
// 			.userDetailsService(new RootUserService(customerManager));
// 	}

// 	@Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 		http
// 			.authorizeRequests()
// 			.antMatchers("/chart","/bill","/cmd","/createc","/deletec","/affichcmd","/").authenticated()
// 			.antMatchers(HttpMethod.POST, "/spittles").authenticated()
// 			.anyRequest().permitAll();
// 	}


// }