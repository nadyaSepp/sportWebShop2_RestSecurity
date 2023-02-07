package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.Receipt;
import ru.seppna.sportwebshop_rest.services.ReceiptService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;

    @GetMapping("/buys")
    public List<Receipt> getAll(){ return receiptService.findAll();
    }

    @PostMapping("/create")
    public Receipt create(@RequestBody Receipt receipt) {
        return receiptService.create(receipt);
    }

}
