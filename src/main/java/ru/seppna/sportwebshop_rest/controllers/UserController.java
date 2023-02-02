package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.services.UserService;
import ru.seppna.sportwebshop_rest.models.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    //admin,superadmin
    //show Users
    @GetMapping
    @PreAuthorize("hasAuthority('user:write')")
    public List<User> getAll(){
        return userService.findAll();
    }

    //admin
    //show User(id)
    @GetMapping("{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public User get(@PathVariable int userId) {
        return userService.findById(userId);
    }

    //admin,superadmin, client
    //show все покупки id-Usera
    @GetMapping("/{id}/buys")
    //@PreAuthorize("hasAuthority('user:read')")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) or hasAuthority('user:write')")
    public List<Buy> allBuys(@PathVariable int id){


        return userService.findById(id).getBuys();
    }

    //admin,superadmin,client
    //регистрация в магазине
    @PreAuthorize("hasAuthority('user:read')")
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    //admin,superadmin, client
    //изменение данных личного кабинета (ФИО,город, страна, контактный телефон)
    @PatchMapping("/{id}/update")
    //@PreAuthorize("hasAuthority('user:read')")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) or hasAuthority('user:write')")
    public User update( @PathVariable(name = "id") int id,
                        @RequestBody User newUser) {
            User user=userService.findById(id);
            user.setFirstname(newUser.getFirstname());
            user.setSurname(newUser.getSurname());
            user.setCity(newUser.getCity());
            user.setCountry(newUser.getCountry());
            user.setPhone(newUser.getPhone());
            userService.save(user);
        return user;
    }

    //admin, superadmin,client
    //создать покупку
    @PostMapping("/{id}/new_buy")
    //@PreAuthorize("hasAuthority('product:read')")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) or hasAuthority('user:write')")
    public Buy buyProducts(@PathVariable(name = "id") int id,
                           @RequestBody List<Receipt> items) {
        Buy buy = userService.commitBuy(id, items);
        items.forEach(receipt -> {
            System.out.println(receipt.getProduct().getId()
                    + " : " +
                    receipt.getCount());
        });
        return buy;
    }

    //superadmin
    //изменение status
    @PatchMapping("/{userId}/status")
    @PreAuthorize("hasAuthority('role:write')")
    public User setStatusUser(@PathVariable("userId") int id,
                              @RequestParam(name="status",required = true) Status status) {
        User user=userService.findById(id);
        user.setStatus(status);
        userService.save(user);
        return user;
    }

    //superadmin
    //изменение role
    @PatchMapping("/{userId}/role")
    @PreAuthorize("hasAuthority('role:write')")
    public User setRoleUser(@PathVariable("userId") int id,
                              @RequestParam(name="role",required = true) Role role) {
        User user=userService.findById(id);
        user.setRole(role);
        userService.save(user);
        return user;
    }

    //admin
    //удалить clienta
    @DeleteMapping("{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void delete(@PathVariable int userId) {
        userService.delete(userId);
    }

}
