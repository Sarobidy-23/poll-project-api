package com.example.prog_final.prog_final.service;

import com.example.prog_final.prog_final.model.Question;
import com.example.prog_final.prog_final.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ResponseService responseService;

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Transactional
    public List<Question> saveAll(List<Question> toAdd) {
        return questionRepository.saveAll(toAdd);
    }

    @Transactional
    public void deleteByPoll(int id_poll) {
        List<Question> toDelete = questionRepository.findById(id_poll);
        questionRepository.deleteAll(toDelete);
        responseService.deleteByPoll(id_poll);
    }

    @Transactional
    public void deleteByIdQuestion(int id_question) {
        Question toDelete = questionRepository.getById(id_question);
        String titleQuestion = toDelete.getTitle();
        responseService.deleteByQuestion(titleQuestion);
        questionRepository.delete(toDelete);
    }
}
