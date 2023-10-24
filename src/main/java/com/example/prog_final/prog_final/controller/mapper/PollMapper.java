package com.example.prog_final.prog_final.controller.mapper;

import com.example.prog_final.prog_final.controller.response.CreatePollResponse;
import com.example.prog_final.prog_final.controller.response.PollResponse;
import com.example.prog_final.prog_final.model.Poll;
import com.example.prog_final.prog_final.model.User;
import com.example.prog_final.prog_final.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PollMapper {
    private final UserService usersService;

    public PollResponse toRest(Poll domain) {
       return PollResponse.builder()
               .id(domain.getId())
               .title(domain.getTitle())
               .creationDatetime(domain.getCreationDatetime().toString())
               .owner(domain.getUser().getUsername())
               .idOwner(domain.getUser().getId()).build();
    }
    public Poll toDomain(CreatePollResponse domain) {
            User associatedUser = usersService.getById(domain.getIdOwner());
        return Poll.builder()
                .user(associatedUser)
                .userid(domain.getIdOwner())
                .title(domain.getTitle())
                .build();
    }

}
