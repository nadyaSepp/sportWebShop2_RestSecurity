package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.Receipt;
import ru.seppna.sportwebshop_rest.services.BuyService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/buys")
public class BuyController {
    private final BuyService buyService;


    //обработка иск.ситуации (тип1)
    //с указанием в Header: своего сообщения-"No buy present!")
    //с указанием в ответе: класса ошибки NoSuchElementException.class
    @ExceptionHandler()
    public ResponseEntity<String> handle(NoSuchElementException ex){
        log.error(ex.getMessage());
        return  new ResponseEntity<>("No buy present!", HttpStatus.NOT_FOUND);
    }
    //admin + super
    //show покупки всех users
    @GetMapping
    @PreAuthorize("hasAuthority('product:write')")
    public List<Buy> getAll(){
        return buyService.findAll();
    }


    //admin + super,client
    //show покупку id
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('product:write')")
    public Buy get(@PathVariable int id) {
        return buyService.findById(id);
    }


    //admin + super
    //показать все товары в чеке(buy) с номером id
    @GetMapping("/{id}/products")
    @PreAuthorize("hasAuthority('product:write')")
    public List<Receipt> allProductsInBy(@PathVariable int id){
        return buyService.findById(id).getReceipts();
    }

    //admin + super
    @GetMapping("/before_date/")
    @PreAuthorize("hasAuthority('product:write')")
    public List<Buy> getByDateBefore(@RequestParam ("value") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date value){
        List<Buy> buys=buyService.findBeforeDate(value);
        return buys;
    }

    //admin + super
    @GetMapping("/after_date/")
    @PreAuthorize("hasAuthority('product:write')")
    public List<Buy> getByDateAfter(@RequestParam ("value") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date value){
        List<Buy> buys=buyService.findAfterDate(value);
        return buys;
    }

}
