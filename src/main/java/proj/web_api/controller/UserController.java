package proj.web_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.web_api.handler.NoUserFoundException;
import proj.web_api.handler.ObligatoryFieldException;
import proj.web_api.model.User;
import proj.web_api.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping()
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{username}")
    public User getOneUser(@PathVariable("username") String username){
        return userRepository.findByLogin(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        if (userRepository.findById(id).isEmpty()){
            throw new NoUserFoundException("Usuário com a id " + id + " não foi encontrado");
        }
        userRepository.deleteById(id);
    }

    @PostMapping()
    public void postUser(@RequestBody User user){
        if(user.getLogin() == null){
            throw new ObligatoryFieldException("login");

        } else if (user.getName() == null){
            throw new ObligatoryFieldException("nome");
            
        } else if(user.getPassword() == null){
            throw new ObligatoryFieldException("senha");

        }
        userRepository.save(user);
    }
}
