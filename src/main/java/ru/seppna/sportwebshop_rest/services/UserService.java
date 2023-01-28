package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.repository.UserRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow();
   }

    public User create(User user) {
        return userRepository.save(user);
    }

    //admin
    public void delete(int id) {
        userRepository.deleteById(id);
    }

}
