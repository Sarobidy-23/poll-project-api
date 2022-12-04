package com.example.prog_final.prog_final.controller;

import com.example.prog_final.prog_final.model.User;
import com.example.prog_final.prog_final.service.UserService;
import com.example.prog_final.prog_final.utility.JWTUtility;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JWTUtility jwtUtility;

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
    public ResponseEntity<?> createAuthToken(@RequestBody User user) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userService
                .loadUserByUsername(user.getUsername());

        final String jwt = jwtUtility.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
}
