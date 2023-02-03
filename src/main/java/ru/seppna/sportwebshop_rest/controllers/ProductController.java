package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.services.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

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
