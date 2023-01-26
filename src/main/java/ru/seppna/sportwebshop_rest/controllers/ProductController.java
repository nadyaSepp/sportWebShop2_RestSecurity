package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.services.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    //admin,user
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping
    public List<Product> getAll(){
        return productService.findAll();
    }

    //поиск товаров по заданным параметрам
    // (название, бренд, цена, категория товара, размер, цвет)
    //@PreAuthorize("hasAuthority('product:read')")

//    @PreAuthorize("hasAuthority('product:read')")
//    @GetMapping("/category/{id}")
//    public List<Product> searchCategory(@PathVariable int id){
//        return productService.searchCategory(id);
//    }

    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping("{id}")
    public Product get(@PathVariable int id) {
        return productService.findById(id);
    }


    //admin
    //добавить товар в магазин
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('product:write')")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PreAuthorize("hasAuthority('product:write')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }
}
