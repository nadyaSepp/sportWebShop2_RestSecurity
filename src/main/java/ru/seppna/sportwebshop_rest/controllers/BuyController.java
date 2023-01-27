package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.Receipt;
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
    //show покупки всех users
    @PreAuthorize("hasAuthority('product:write')")
    @GetMapping
    public List<Buy> getAll(){
        return buyService.findAll();
    }
//+    public List<Product> getAll(){
//        return buyService.allProducts();
//    }

    //admin,client
    //show покупку id
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping("{id}")
    public Buy get(@PathVariable int id) {
        return buyService.findById(id);
    }

    //-  поиск покупки по user(id) и дате покупки
//    @GetMapping("/date/{id}/{}")
//    public List<Buy> getUserDate(@PathVariable User user, Date value) {
//        return buyService.findDistinctByUseridAndRegistration(user,value);
//    }

    //admin,client
    //показать все позиции(товары) в чеке(покупке) с номером id
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping("/{id}/products")
    public List<Receipt> allProductsInBy(@PathVariable int id){
        return buyService.findById(id).getReceipts();
    }

    // - admin,client
    //создать покупку User(id)
//    @PreAuthorize("hasAuthority('product:write')")
//    @PostMapping("/create/{id}")
//    public Buy create(@PathVariable User user, @RequestBody List<Receipt> receipts) {
//        Buy buy = new Buy(new Date(), user);
//        buyService.create(buy);
//        //buy.getId()
//        //buy.setUser();
//        //+receipts;
//        return buy;
//    }

    //+ - admin,client
    //создать покупку
//    @PreAuthorize("hasAuthority('product:read')")
//    @PostMapping("/create/{id}/")
//    public Buy create(@PathVariable User user,@RequestBody List<Receipt> receipts) {
//        //добавить проверку на валидность!!!
//        //....
//        //здесь потом получить user,связанного с сессией
//        //User user = new User();
//        //Buy buy = new Buy(21, new Date(), user, receipts);
//        int userId=user.getId();
//
//        Buy buy = new Buy(9,new Date(), userId, receipts);
//        return buyService.create(buy);
//    }

}
