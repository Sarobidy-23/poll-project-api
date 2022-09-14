package com.example.prog_final.prog_final.controller;

import com.example.prog_final.prog_final.model.Users;
import com.example.prog_final.prog_final.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@Transactional
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/users")
    public List<Users> getAll() {
        return usersService.getAll();
    }

    @PostMapping("/users")
    public Users saveAll(@RequestBody Users toAdd) {
        return usersService.save(toAdd);
    }

    @DeleteMapping("/users/{id_user}")
    public void deleteById(@PathVariable int id_user) {
         usersService.deleteById(id_user);
    }

}
