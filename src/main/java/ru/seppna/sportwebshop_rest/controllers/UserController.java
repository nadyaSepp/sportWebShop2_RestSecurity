package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.services.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //admin
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping
    public List<User> getAll(){
        return userService.findAll();
    }

    //admin, user
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("{id}")
    public User get(@PathVariable int id) {
        return userService.findById(id);
    }

    //admin, user
    //show все покупки Usera
    @GetMapping("/{id}/buys")
    public List<Buy> allBuys(@PathVariable int id){
        return userService.findById(id).getBuys();
    }

    //admin
    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    //admin, user
    //putch информацию личного кабинета
    //@PreAuthorize("hasAuthority('user:write')")

    //admin
    //putch is_block,is_admin
    //@PreAuthorize("hasAuthority('user:write')")

    //admin
    //dalete
    //@PreAuthorize("hasAuthority('user:write')")

}
