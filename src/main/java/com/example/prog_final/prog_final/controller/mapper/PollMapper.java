package com.example.prog_final.prog_final.controller.mapper;

import com.example.prog_final.prog_final.model.Poll;
import com.example.prog_final.prog_final.model.Users;
import com.example.prog_final.prog_final.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Component
@AllArgsConstructor
public class PollMapper {
    private final UsersService usersService;

    public Poll toRestPoll(Poll poll) {
        Poll toReturn = new Poll();
                toReturn.setIdPoll(poll.getIdPoll());
                toReturn.setTitle(poll.getTitle());
                toReturn.setCreationDatetime(poll.getCreationDatetime());
                toReturn.setUsers(poll.getUsers());
        return toReturn;
    }
    private Poll toDomainPoll(
            Users associatedUser, Poll createPoll) {
        return Poll.builder()
                .users(associatedUser)
                .idOwner(createPoll.getIdOwner())
                .title(createPoll.getTitle())
                .creationDatetime(createPoll.getCreationDatetime())
                .build();
    }
    public List<Poll> toDomainPoll(
            int user_id, List<Poll> createdPoll) {
        Users associatedUser = usersService.getById(user_id);

        return createdPoll.stream()
                .map(payment -> toDomainPoll(associatedUser, payment))
                .collect(toUnmodifiableList());
    }
}
