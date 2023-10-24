package com.example.prog_final.prog_final.security;


import com.example.prog_final.prog_final.model.User;
import com.example.prog_final.prog_final.model.exception.NotFoundException;
import com.example.prog_final.prog_final.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);
        if(user.isPresent()) {
            return new UserPrincipale(user.get());
        }else {
            throw  new NotFoundException("user.username="+s+"NotFound");
        }

    }
}