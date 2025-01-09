package proj.web_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/managersAuth").hasRole("manager")
                .requestMatchers("/usersAuth").hasAnyRole("user","manager")
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults());
        return http.build();
    }

        @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = org.springframework.security.core.userdetails.User.builder()
            .username("user")
            .password("{noop}user123")
            .roles("user")
            .build();

        
        UserDetails admin = org.springframework.security.core.userdetails.User.builder()
            .username("admin")
            .password("{noop}admin123") 
            .roles("manager")
            .build();

        
        return new InMemoryUserDetailsManager(user, admin);
    }
}
