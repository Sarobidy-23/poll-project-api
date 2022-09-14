package com.example.prog_final.prog_final.controller;

import com.example.prog_final.prog_final.controller.mapper.ResponseMapper;
import com.example.prog_final.prog_final.model.Response;
import com.example.prog_final.prog_final.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ResponseController {
    private final ResponseService responseService;
    private final ResponseMapper responseMapper;

    @GetMapping("/responses")
    public List<Response> getAll() {
        return responseService.getAll();
    }

    @PostMapping("/responses")
    public List<Response> saveAll(@RequestBody List<Response> toAdd) {
        return responseService.saveAll(toAdd);
    }

    @PostMapping("/responses/poll/{id_poll}/owner/{id_owner}")
    public List<Response> saveAll(@RequestBody List<Response> toAdd,
                                  @PathVariable int id_poll, @PathVariable int id_owner) {
        return responseService.saveAll(responseMapper.toDomainResponse(id_poll,id_owner, toAdd));
    }

    @DeleteMapping("/responses/poll/{id_poll}")
    public void deleteByOwner(@PathVariable int id_poll) {
        responseService.deleteByPoll(id_poll);
    }
}
