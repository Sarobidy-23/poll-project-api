package com.example.prog_final.prog_final.service;

import com.example.prog_final.prog_final.model.User;
import com.example.prog_final.prog_final.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  repository.findUsersByUsername(username);
        return new com.example.prog_final.prog_final.service.UserDetailsService(user);
    }
    @Transactional
    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User getById(long id){
        return repository.getById(id);
    }

    @Transactional
    public void deleteById(Long toRemove) {
        User toDel = repository.getById(toRemove);

        repository.delete(toDel);
    }
}