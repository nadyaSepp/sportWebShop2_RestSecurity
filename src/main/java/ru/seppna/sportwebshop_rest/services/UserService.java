package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.repositories.UserRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //admin
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //admin
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow();
   }

    //admin, user
    public User create(User user) {
        return userRepository.save(user);
    }

    //admin, user
    //putch информацию личного кабинета

    //admin
    //putch is_block,is_admin

    //admin
    //dalete

}
