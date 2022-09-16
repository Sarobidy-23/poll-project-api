package com.example.prog_final.prog_final.service;

import com.example.prog_final.prog_final.model.Poll;
import com.example.prog_final.prog_final.repository.PollRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PollService {
    private final PollRepository pollRepository;
    private final QuestionService questionService;

    public List<Poll> getAll() {
        return pollRepository.findAll();
    }

    @Transactional
    public List<Poll> saveAll(List<Poll> toAdd) {
        return pollRepository.saveAll(toAdd);
    }

    public Poll getById(int id_poll) {
        return pollRepository.getById(id_poll);
    }

    @Transactional
    public Poll modifyTitle (int id, String titleToAdd) {
        Poll poll = pollRepository.getById(id);
        poll.setTitle(titleToAdd);
        return pollRepository.save(poll);
    }

   public List<Poll> getAllByOwner(int id_user) {
        return pollRepository.findByIdOwner(id_user);
    }

    public String deleteAllQuestion(int id_poll) {
        questionService.deleteByPoll(id_poll);
        return "vita";
    }
    @Transactional
    public void deleteByOwner(int id_user) {
        List<Poll> toDelete = pollRepository.findByIdOwner(id_user);
        toDelete.stream().map(m -> deleteAllQuestion(m.getIdPoll()));
        pollRepository.deleteAll(toDelete);
    }

    @Transactional
    public void deleteById(int id_poll) {
        Poll toDelete = pollRepository.getById(id_poll);
        deleteAllQuestion(toDelete.getIdPoll());
        pollRepository.delete(toDelete);
    }

}
