package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.error.ErrorMessage;
import ru.seppna.sportwebshop_rest.error.NoSuchProductException;
import ru.seppna.sportwebshop_rest.services.UserService;
import ru.seppna.sportwebshop_rest.models.*;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    //обработка иск.ситуации (тип1)
    //с указанием в Header: своего сообщения-"No user present!")
    //с указанием в ответе: класса ошибки NoSuchElementException.class
    @ResponseStatus(HttpStatus.NOT_FOUND) //чтоб статус ответа тоже остался ошибкой -"404 NOT FOUND"
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(){
        return  new ResponseEntity<>("No user present!", HttpStatus.NOT_FOUND);
    }

    //или обработка иск.ситуаций (тип 3 - свой обработчик NoSuchProductException + его сообщ. и сообщения "No such product")
    // с указанием в ответе- "400 BAD_REQUEST"
    //@ExceptionHandler(NoSuchProductException.class)

    //класс exception EntityExistsException.class
    @ExceptionHandler(EntityExistsException.class)
     public ResponseEntity<String> handleBadEmail(){
        return  new ResponseEntity<>("Bad email!", HttpStatus.BAD_REQUEST);
    }

    //класс exception IllegalArgumentException.class
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleExistsEmail(){
        return  new ResponseEntity<>("Already email exists!", HttpStatus.BAD_REQUEST);
    }

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
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) or hasAuthority('user:write')")
    public List<Buy> allBuys(@PathVariable int id){
        return userService.findById(id).getBuys();
    }

    //all
    //регистрация в магазине
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    //admin,superadmin, client
    //изменение данных личного кабинета (ФИО,город, страна, контактный телефон)
    @PatchMapping("/{id}/update")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) or hasAuthority('user:write')")
    public User update( @PathVariable(name = "id") int id,
                        @RequestBody User newUser) {
        return userService.update(id,newUser);
    }

    //admin, superadmin,client
    //создать покупку
    @PostMapping("/{id}/create_buy")
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

    //admin,superadmin
    //изменение status
    @PatchMapping("/{userId}/status")
    @PreAuthorize("hasAuthority('user:write')")
    public User setStatusUser(@PathVariable("userId") int id,
                              @RequestParam(name="status",required = true) Status status) {
        return userService.setStatusUser(id,status);
    }

    //superadmin
    //изменение role
    @PatchMapping("/{userId}/role")
    @PreAuthorize("hasAuthority('role:write')")
    public User setRoleUser(@PathVariable("userId") int id,
                              @RequestParam(name="role",required = true) Role role) {
        return userService.setRoleUser(id,role);
    }

    //admin
    //удалить clienta
    @DeleteMapping("{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public int delete(@PathVariable int userId) {
        int id=userService.delete(userId);
        return  id;
    }

}
