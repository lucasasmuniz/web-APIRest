package proj.web_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Autowired
    private SecurityDatabaseService securityService;
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/managersAuth").hasRole("manager")
                .requestMatchers("/usersAuth").hasAnyRole("user","manager")
                .requestMatchers("/users").hasAnyRole("user","manager")
                .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("user", "manager") // Permite GET para roles específicas
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("manager") // Permite DELETE apenas para manager
                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .csrf().disable();
        return http.build();
    }

    //Salvando usuários em memória
    //     @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = org.springframework.security.core.userdetails.User.builder()
    //         .username("user")
    //         .password("{noop}user123")
    //         .roles("user")
    //         .build();

    //     UserDetails admin = org.springframework.security.core.userdetails.User.builder()
    //         .username("admin")
    //         .password("{noop}admin123") 
    //         .roles("manager")
    //         .build();

        
    //     return new InMemoryUserDetailsManager(user, admin);
    // }
}
