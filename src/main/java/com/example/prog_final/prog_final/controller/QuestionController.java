package com.example.prog_final.prog_final.controller;

import com.example.prog_final.prog_final.controller.mapper.QuestionMapper;
import com.example.prog_final.prog_final.model.Question;
import com.example.prog_final.prog_final.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@RestController
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/questions")
    public List<Question> getAll() {
        return questionService.getAll();
    }

    @PostMapping("/questions")
    public List<Question> saveAll(@RequestBody List<Question> toAdd) {
        return questionService.saveAll(toAdd);
    }

    @PutMapping(value = "/questions")
    public List<Question> createEventOrUpdate(@RequestBody List<Question> toWrite) {
        var saved = questionService.saveAll(toWrite.stream()
                .map(questionMapper::toDomain)
                .collect(toUnmodifiableList()));
        return saved.stream()
                .map(questionMapper::toRest)
                .collect(toUnmodifiableList());
    }
    @PostMapping("/questions/poll/{id_poll}")
    public List<Question> save(@PathVariable int id_poll, @RequestBody List<Question> toAdd) {
        return questionService.saveAll(questionMapper.toDomainQuestion(id_poll, toAdd));
    }

    @DeleteMapping("/questions/poll/{id_poll}")
    public void deleteByPoll(@PathVariable int id_poll) {
        questionService.deleteByPoll(id_poll);
    }

    @DeleteMapping("/questions/{id_question}")
    public void deleteById(@PathVariable int id_question) {
        questionService.deleteByIdQuestion(id_question);
    }
}
