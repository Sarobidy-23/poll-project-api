package com.example.prog_final.prog_final.service;

import com.example.prog_final.prog_final.model.Response;
import com.example.prog_final.prog_final.repository.ResponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ResponseService {
    private final ResponseRepository responseRepository;

    public List<Response> getAll() {
        return responseRepository.findAll();
    }

    @Transactional
    public List<Response> saveAll(List<Response> toAdd) {
        return responseRepository.saveAll(toAdd);
    }

    @Transactional
    public void deleteByPoll(int id_poll) {
        List<Response> toDelete = responseRepository.findByIdPoll(id_poll);
        responseRepository.deleteAll(toDelete);
    }

    @Transactional
    public void deleteByQuestion(String question) {
        List<Response> toDelete = responseRepository.findByQuestion(question);
        responseRepository.deleteAll(toDelete);
    }

}