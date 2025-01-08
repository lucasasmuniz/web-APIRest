package proj.web_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll() // Permite acesso público à raiz
                .requestMatchers(HttpMethod.POST, "/login").permitAll() // Permite acesso público à página de login
                .requestMatchers("/managerAuth").hasRole("manager") // Apenas usuários com o papel "manager"
                .requestMatchers("/usersAuth").hasAnyRole("user", "manager") // Usuários com os papéis "user" ou "manager"
                .anyRequest().authenticated() // Todas as outras solicitações precisam de autenticação
            );

        return http.build();
    }
}
