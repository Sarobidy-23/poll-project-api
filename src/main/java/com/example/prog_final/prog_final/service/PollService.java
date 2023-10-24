package com.example.prog_final.prog_final.service;

import com.example.prog_final.prog_final.model.Poll;
import com.example.prog_final.prog_final.model.exception.NotFoundException;
import com.example.prog_final.prog_final.repository.PollRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PollService {
    private final PollRepository pollRepository;
    private final QuestionService questionService;

    public List<Poll> getAll() {
        return pollRepository.findAll();
    }
    public Poll getById(int id_poll) {
        Optional<Poll> optional = pollRepository.findById(id_poll);
        if(optional.isPresent()) {
            return optional.get();
        }
        else {
            throw new NotFoundException("Poll.id"+id_poll+"NotFound");
        }
    }
    @Transactional
    public List<Poll> saveAll(List<Poll> toAdd)  {
        return pollRepository.saveAll(toAdd);
    }
    @Transactional
    public Poll modifyTitle (int id, String titleToAdd) {
        Poll poll = pollRepository.getById(id);
        poll.setTitle(titleToAdd);
        return pollRepository.save(poll);
    }

    public List<Poll> getAllByOwner(int id_user) {
        return pollRepository.findAllByUserid(id_user);
    }

    public String deleteAllQuestion(int id_poll) {
        questionService.deleteByPoll(id_poll);
        return "vita";
    }
    @Transactional
    @Async
    public void deleteByOwner(int id_user) {
        List<Poll> toDelete = pollRepository.findAllByUserid(id_user);
        toDelete.stream().map(m -> deleteAllQuestion(m.getId()));
        pollRepository.deleteAll(toDelete);
    }

    @Transactional
    public void deleteById(int id_poll) {
        Poll toDelete = pollRepository.getById(id_poll);
        deleteAllQuestion(toDelete.getId());
        pollRepository.delete(toDelete);
    }

}
