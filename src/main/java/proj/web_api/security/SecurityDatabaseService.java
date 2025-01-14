// package proj.web_api.security;

// import java.util.HashSet;
// import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import proj.web_api.model.User;
// import proj.web_api.repository.UserRepository;

// @Service
// public class SecurityDatabaseService implements UserDetailsService{
//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String login){
//         User userEntity = userRepository.findByLogin(login);
//         if (userEntity == null){
//             throw new UsernameNotFoundException(login);
//         }
//         Set<GrantedAuthority> authorities = new HashSet<>();
//         userEntity.getRoles().forEach(role ->{
//             authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
//         });
//         UserDetails user = new org.springframework.security.core.userdetails.User(userEntity.getLogin(),
//             userEntity.getPassword(),
//             authorities);
//         return user;
//     }

// }
