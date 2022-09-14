package com.example.prog_final.prog_final.repository;

import com.example.prog_final.prog_final.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {
    List<Response> findByIdPoll(int id_poll);
    List<Response> findByQuestion(String question);
}
