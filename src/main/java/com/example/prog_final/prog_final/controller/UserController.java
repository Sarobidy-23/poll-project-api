package com.example.prog_final.prog_final.controller;

import com.example.prog_final.prog_final.model.User;
import com.example.prog_final.prog_final.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {



    @Autowired
    private  final UserService userService;
    @PostMapping("/user")
    public User post(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/user/{username}")
    public UserDetails getDetails(@PathVariable String username){
        return userService.loadUserByUsername(username);
    }

    @PostMapping("/login")
    public String createAuthToken(@RequestBody User user) throws Exception{
        return userService.createAuthToken(user);
    }
}
