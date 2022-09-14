package com.example.prog_final.prog_final.service;

import com.example.prog_final.prog_final.model.Users;
import com.example.prog_final.prog_final.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PollService pollService;

    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public Users save(Users toAdd) {
        return usersRepository.save(toAdd);
    }
    public Users getById(int user_id) {
        return usersRepository.getReferenceById(user_id);
    }

    @Transactional
    public void deleteById(int toRemove) {
             Users toDel = usersRepository.getById(toRemove);
             int id_user = toDel.getId_user();
             pollService.deleteByOwner(id_user);
             usersRepository.delete(toDel);
    }
}
