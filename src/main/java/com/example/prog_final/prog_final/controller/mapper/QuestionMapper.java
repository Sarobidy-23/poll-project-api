package com.example.prog_final.prog_final.controller.mapper;

import com.example.prog_final.prog_final.model.Poll;
import com.example.prog_final.prog_final.model.Question;
import com.example.prog_final.prog_final.service.PollService;
import com.example.prog_final.prog_final.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Component
@AllArgsConstructor
public class QuestionMapper {
    public final QuestionService questionService;
    private final PollService pollService;
    public Question toRest(Question question) {
        var restQuestion = new Question();
        restQuestion.setId(question.getId());
        restQuestion.setTitle(question.getTitle());
        restQuestion.setType(question.getType());
        restQuestion.setPossible_response(question.getPossible_response());
        restQuestion.setOrder_in_poll(question.getOrder_in_poll());
        restQuestion.setRequired(question.isRequired());
        restQuestion.setCreation_datetime(question.getCreation_datetime());
        restQuestion.setId(question.getId());

        return restQuestion;
    }

    public Question toDomain(Question restQuestion) {
        return Question.builder()
                .id(restQuestion.getId())
                .title(restQuestion.getTitle())
                .type(restQuestion.getType())
                .possible_response(restQuestion.getPossible_response())
                .order_in_poll(restQuestion.getOrder_in_poll())
                .required(restQuestion.isRequired())
                .creation_datetime(restQuestion.getCreation_datetime())
                .id(restQuestion.getId())
                .build();
    }

    private Question toDomainQuestion(
            Poll associatedPoll, Question createQuestion) {
        return Question.builder()
                .id(createQuestion.getId())
                .title(createQuestion.getTitle())
                .type(createQuestion.getType())
                .possible_response(createQuestion.getPossible_response())
                .order_in_poll(createQuestion.getOrder_in_poll())
                .required(createQuestion.isRequired())
                .creation_datetime(createQuestion.getCreation_datetime())
                .id(createQuestion.getId())
                .poll(associatedPoll)
                .build();
    }
    public List<Question> toDomainQuestion(
            int id_poll, List<Question> createdQuestion) {
        Poll associatedPoll = pollService.getById(id_poll);

        return createdQuestion.stream()
                .map(question -> toDomainQuestion(associatedPoll, question))
                .collect(toUnmodifiableList());
    }

}
