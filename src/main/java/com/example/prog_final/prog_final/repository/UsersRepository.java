package com.example.prog_final.prog_final.repository;

import com.example.prog_final.prog_final.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsById(int id);
}
