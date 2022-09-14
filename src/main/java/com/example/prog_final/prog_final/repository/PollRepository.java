package com.example.prog_final.prog_final.repository;

import com.example.prog_final.prog_final.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {
   List<Poll> findByIdOwner(int id_user);
}
