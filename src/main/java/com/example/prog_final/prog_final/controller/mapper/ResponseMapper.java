package com.example.prog_final.prog_final.controller.mapper;

import com.example.prog_final.prog_final.model.Poll;
import com.example.prog_final.prog_final.model.Response;
import com.example.prog_final.prog_final.model.Users;
import com.example.prog_final.prog_final.service.PollService;
import com.example.prog_final.prog_final.service.ResponseService;
import com.example.prog_final.prog_final.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Component
@AllArgsConstructor
public class ResponseMapper {
    private final ResponseService responseService;
    private final  PollService pollService;
    private final UsersService usersService;

    public Response toRestResponse(Response response) {
        Response toReturn = new Response();
        toReturn.setIdResponse(response.getIdResponse());
        toReturn.setQuestion(response.getQuestion());
        toReturn.setChooseResponse(response.getChooseResponse());
        toReturn.setIdPoll(response.getIdPoll());
        toReturn.setPoll(response.getPoll());
        toReturn.setIdOwner(response.getIdOwner());
        toReturn.setUsers(response.getUsers());
        toReturn.setChoiceDatetime(response.getChoiceDatetime());
        return toReturn;
    }
    private Response toDomainResponse(
            Poll associatedPoll,
            Users associatedUser,
            Response createdResponse) {
        return Response.builder()
                .users(associatedUser)
                .poll(associatedPoll)
                .idResponse(createdResponse.getIdResponse())
                .question(createdResponse.getQuestion())
                .chooseResponse(createdResponse.getChooseResponse())
                .idPoll(createdResponse.getIdPoll())
                .idOwner(createdResponse.getIdOwner())
                .choiceDatetime(createdResponse.getChoiceDatetime())
                .build();
    }
    public List<Response> toDomainResponse(
            int id_poll,
            int id_owner,
            List<Response> createdResponse) {
        Poll associatedPoll = pollService.getById(id_poll);
        Users associatedUser = usersService.getById(id_owner);

        return createdResponse.stream()
                .map(response -> toDomainResponse(associatedPoll, associatedUser, response))
                .collect(toUnmodifiableList());
    }
}
