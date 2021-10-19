package com.PI.ProjetoIntegrador.controler;

import com.PI.ProjetoIntegrador.model.User;
import com.PI.ProjetoIntegrador.model.UserLogin;
import com.PI.ProjetoIntegrador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/logging")
    public ResponseEntity<UserLogin> Authentication(@RequestBody Optional<UserLogin> user){
        return userService.signIn(user).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/registration")
    public ResponseEntity<User> Post(@Valid @RequestBody User user){

        return userService.userRegistration(user);
    }
}
