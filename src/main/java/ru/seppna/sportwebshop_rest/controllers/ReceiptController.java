package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.Receipt;
import ru.seppna.sportwebshop_rest.services.ReceiptService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;

    @ResponseStatus(HttpStatus.NOT_FOUND) //чтоб статус ответа тоже остался ошибкой -"404 NOT FOUND"
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(){
        return  new ResponseEntity<>("No receipts present!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buys")
    public List<Receipt> getAll(){ return receiptService.findAll();
    }

    @PostMapping("/create")
    public Receipt create(@RequestBody Receipt receipt) {
        return receiptService.create(receipt);
    }

}
