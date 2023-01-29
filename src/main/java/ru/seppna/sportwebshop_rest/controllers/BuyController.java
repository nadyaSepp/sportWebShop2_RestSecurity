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

    //+ public List<Product> getAll(){
    //     return buyService.allProducts();
    //  }

    //admin,client
    //show покупку id (доработать - поиск покупки по дате)
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping("{id}")
    public Buy get(@PathVariable int id) {
        return buyService.findById(id);
    }


    //admin,client
    //показать все товары в покупке(чеке) с номером id
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping("/{id}/products")
    public List<Receipt> allProductsInBy(@PathVariable int id){
        return buyService.findById(id).getReceipts();
    }

}
