package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.error.ErrorMessage;
import ru.seppna.sportwebshop_rest.error.NoSuchProductException;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.services.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    //обработка иск.ситуаций (тип 3 - свой обработчик NoSuchProductException + его сообщ. и сообщения "No such product")
    // с указанием статуса в Header: HttpStatus.NOT_FOUND)
    // с указанием в ответе- "404 NOT FOUND"
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND) //чтоб статус ответа тоже остался ошибкой -"404 NOT FOUND"
    public ErrorMessage handle(NoSuchProductException ex){
        log.error(ex.getMessage());
        return  new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(){
        return  new ResponseEntity<>("No product present!", HttpStatus.NOT_FOUND);
    }

    //admin,client
    //просмотр всех товаров
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping
    public List<Product> getAll(){
        return productService.findAll();
    }

    //admin,client
    //просмотр товара (id)
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping("{id}")
    public Product get(@PathVariable int id) {
        return productService.findById(id);
    }

    //admin
    //добавить товар в магазин
    @PatchMapping("/create")
    @PreAuthorize("hasAuthority('product:write')")
    public Product create(@RequestBody Product product) {

        return productService.create(product);
    }

    //admin
    //удалить товар из магазина
    @PreAuthorize("hasAuthority('product:write')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }
}
