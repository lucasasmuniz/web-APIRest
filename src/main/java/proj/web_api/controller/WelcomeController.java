package proj.web_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public String welcome(){
        return "Welcome to My Spring Boot Web API";
    }

    @GetMapping("/usersAuth")
    public String users(){
        return "Usuário autorizado";
    }

    @GetMapping("/managersAuth")
    public String managers(){
        return "Admin autorizado";
    }
}
