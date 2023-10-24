package com.example.prog_final.prog_final.service;

import com.example.prog_final.prog_final.model.User;
import com.example.prog_final.prog_final.repository.UserRepository;
import com.example.prog_final.prog_final.utility.JWTUtility;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import org.springframework.security.authentication.AuthenticationManager;



@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final AuthenticationManager authenticationManager;
    private final JWTUtility jwtUtility;

    private final UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  repository.findUsersByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
    @Transactional
    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User getById(long id){
        return repository.getById(id);
    }
    public String createAuthToken(User user) throws Exception {
        final UserDetails userDetails = loadUserByUsername(user.getUsername());
            if(userDetails != null) {
                    authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
                    );
                    final String jwt = jwtUtility.generateToken(userDetails);
                return jwt;
            } else {
                throw new Exception("");
            }
    }
}