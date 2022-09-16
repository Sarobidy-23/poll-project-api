package com.example.prog_final.prog_final.controller;

import com.example.prog_final.prog_final.controller.mapper.PollMapper;
import com.example.prog_final.prog_final.model.Poll;
import com.example.prog_final.prog_final.service.PollService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class PollController {
    private final PollService pollService;
    private final PollMapper pollMapper;

    @GetMapping("/polls")
    public List<Poll> getAll() {
        return pollService.getAll();
    }

    @GetMapping("/polls/{id_user}")
    public List<Poll> getAllByOwner(@PathVariable int id_user) {
        return pollService.getAllByOwner(id_user);
    }

    @PostMapping("/polls/{id_user}")
    public List<Poll> save(@PathVariable Long id_user, @RequestBody List<Poll> toAdd) {
        return pollService.saveAll(pollMapper.toDomainPoll(id_user, toAdd));
    }
    @GetMapping("/ping")
    public String hello () {
        return "hello";
    }

    @PatchMapping("/polls/{id_poll}/{title}")
    public Poll modifyTitle(@PathVariable int id_poll, @PathVariable String title) {
        return pollService.modifyTitle(id_poll, title);
    }

    @DeleteMapping("/polls/user/{id_user}")
    public void deleteByUser(@PathVariable int id_user) {
        pollService.deleteByOwner(id_user);
    }

    @DeleteMapping("/polls/{id_poll}")
    public void deleteById(@PathVariable int id_poll) {
        pollService.deleteById(id_poll);
    }
}