package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Role;
import ru.seppna.sportwebshop_rest.models.Status;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.services.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //admin
    //show Users
    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping
    public List<User> getAll(){
        return userService.findAll();
    }

    //admin
    //show User(id)
    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("{id}")
    public User get(@PathVariable int id) {
        return userService.findById(id);
    }

    //admin, client
    //show все покупки id-Usera
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/{id}/buys")
    public List<Buy> allBuys(@PathVariable int id){
        return userService.findById(id).getBuys();
    }

    //admin
    //регистрация в магазине
    @PreAuthorize("hasAuthority('user:read')")
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    //admin, client
    //изменение данных личного кабинета
    @PreAuthorize("hasAnyAuthority('user:read')")
    @PatchMapping("/{id}/{surname}/{city}/{country}/{phone}")
    public User patchClient(@PathVariable("id") int id,
                            @PathVariable("surname") String surname,
                            @PathVariable("city") String city,
                            @PathVariable("country") String country,
                            @PathVariable("phone") String phone) {
        User user=userService.findById(id);
        user.setSurname(surname);
        user.setCity(city);
        user.setCountry(country);
        user.setPhone(phone);
        userService.create(user);
        return user;
    }

    //admin
    //putch status
    @PreAuthorize("hasAuthority('user:write')")
    @PatchMapping("/status/{id}/")
    public User setStatusUser(@PathVariable("id") int id,
                              @RequestParam(name="status",required = true) Status status) {
        User user=userService.findById(id);
        user.setStatus(status);
        userService.create(user);
        return user;
    }

    //admin
    //update role
    @PreAuthorize("hasAuthority('user:write')")
    @PatchMapping("/role/{id}/")
    public User setRoleUser(@PathVariable("id") int id,
                              @RequestParam(name="role",required = true) Role role) {
        User user=userService.findById(id);
        user.setRole(role);
        userService.create(user);
        return user;
    }

    //admin
    //update is_block
    @PreAuthorize("hasAuthority('user:write')")
    @PatchMapping("/block/{id}/")
    public User setBlockUser(@PathVariable("id") int id,
                              @RequestParam(name="is_blok",required = true) int is_block) {
        User user=userService.findById(id);
        user.setIsBlock(is_block);
        userService.create(user);
        return user;
    }

    //admin
    //update is_admin
    @PreAuthorize("hasAuthority('user:write')")
    @PatchMapping("/admin/{id}/")
    public User setAdminUser(@PathVariable("id") int id,
                             @RequestParam(name="is_admin",required = true) int is_admin) {
        User user=userService.findById(id);
        user.setIsAdmin(is_admin);
        userService.create(user);
        return user;
    }
}
