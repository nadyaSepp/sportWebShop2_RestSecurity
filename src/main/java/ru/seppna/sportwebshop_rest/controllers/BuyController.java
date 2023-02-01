package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Receipt;
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
    @GetMapping
    @PreAuthorize("hasAuthority('product:write')")
    public List<Buy> getAll(){
        return buyService.findAll();
    }

    //+ public List<Product> getAll(){
    //     return buyService.allProducts();
    //  }

    //admin,client
    //show покупку id
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('product:write')")
    public Buy get(@PathVariable int id) {
        return buyService.findById(id);
    }

    //---
    //- admin
    @GetMapping("/before_date/")
    @PreAuthorize("hasAuthority('product:write')")
    public List<Receipt> getByDateBefore(@RequestParam ("value") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date value){
        List<Receipt> receipts=buyService.findBeforeDate(value);
        return receipts;
    }
    //------


    //admin
    //показать все товары в чеке(buy) с номером id
    @GetMapping("/{id}/products")
    @PreAuthorize("hasAuthority('product:write')")
    public List<Receipt> allProductsInBy(@PathVariable int id){
        return buyService.findById(id).getReceipts();
    }



}
