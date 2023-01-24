package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.services.BuyService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/buys")
public class BuyController {
    private final BuyService buyService;

    //admin
    //show все покупки всех users
    @GetMapping
    public List<Buy> getAll(){
        return buyService.findAll();
    }
//    public List<Product> getAll(){
//        return buyService.allProducts();
//    }

    //admin,user
    //show покупку id
    @GetMapping("{id}")
    public Buy get(@PathVariable int id) {
        return buyService.findById(id);
    }

    //поиск покупки по user(id) и дате покупки
//    @GetMapping("/date/{id}")
//    public List<Buy> getUserDate(@PathVariable User user, Date value) {
//        return buyService.findDistinctByUseridAndRegistration(user,value);
//    }

    //admin
    //добавить товар в магазин
    @PostMapping("/create")
    public Buy create(@RequestBody Buy buy) {
        return buyService.create(buy);
    }

    //admin
    //удалить покупку id
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        buyService.delete(id);
    }
}
