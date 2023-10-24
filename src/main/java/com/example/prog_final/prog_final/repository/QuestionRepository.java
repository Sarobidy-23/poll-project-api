package com.example.prog_final.prog_final.repository;

import com.example.prog_final.prog_final.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findById(int id_poll);
}
