package proj.web_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.web_api.model.User;
import proj.web_api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    
    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){

        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getOneUser(@PathVariable("username") String username){
        return ResponseEntity.ok(service.findUserByLogin(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id){
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<User> postUser(@RequestBody User user){
        service.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable Integer id,@RequestBody User user){
        service.refreshUserData(id, user);
        return ResponseEntity.ok().build();
    }
}
