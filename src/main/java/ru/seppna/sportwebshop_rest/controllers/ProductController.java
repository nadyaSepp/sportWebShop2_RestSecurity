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
    public ErrorMessage handleProd(NoSuchProductException ex){
        log.error(ex.getMessage());
        return  new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
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

    //admin,client
    //поиск товара по category,brand,price,size
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping("/search")
    public List<Product> get(@RequestParam(name="category",required = true) String category,
                            @RequestParam(name="brand",required = true) String brand,
                            @RequestParam(name="price",required = true) Double price,
                            @RequestParam(name="size",required = true) Double size) {
        return productService.search(category,brand,price,size);
    }

    //admin
    //добавить товар в магазин
    @PatchMapping("/create")
    @PreAuthorize("hasAuthority('product:write')")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    //admin
    //добавить товар в магазин
    @PatchMapping("/{id}/presenсe")
    @PreAuthorize("hasAuthority('product:write')")
    public Product setPresence(@PathVariable("id") int id,
                              @RequestParam(name="value",required = true) String presence) {
        System.out.println(presence);
        return productService.setPresence(id,presence);
    }

    //admin
    //удалить товар из магазина (пока поставить отсутствие в магазине!)
    @PreAuthorize("hasAuthority('product:write')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }
}
