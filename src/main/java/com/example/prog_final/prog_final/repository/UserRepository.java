package com.example.prog_final.prog_final.repository;

import com.example.prog_final.prog_final.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsById(int id);
    User getById(long id);
    User findUsersByUsername(String username);
}