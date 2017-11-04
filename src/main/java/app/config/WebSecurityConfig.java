// package app.config;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.web.context.request.RequestAttributes;
// import org.springframework.web.context.request.RequestContextHolder;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .authorizeRequests()
//                 .antMatchers("/public/**").permitAll() 
//                 .anyRequest().authenticated() 
//                 .and()
//             .formLogin()
//                 .loginPage("/login").permitAll()
//                 .and()
//             .logout()
//                 .permitAll();
//     }

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//         auth.authenticationProvider(
//         		 new AuthenticationProvider() {
        			 
//         			    @Override
//         			    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//         			        String name = authentication.getName();
//         			        String password = authentication.getCredentials().toString();
        			        
//         			        if ( name.equals(password) ) {
//         			            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
//         			            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
//         			            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
        			            
//         			            return auth;
//         			        } else {
//         			            return null;
//         			        }
//         			    }
        			 
//         			    @Override
//         			    public boolean supports(Class<?> authentication) {
//         			        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//         			    }

//         			}        		
//         		);
//     }
// }