package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
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
    @GetMapping
    public List<Product> getAll(){
        return productService.findAll();
    }

    //поиск товаров по заданным параметрам
    // (название, бренд, цена, категория товара, размер, цвет)

//    @GetMapping("/category/{id}")
//    public List<Product> searchCategory(@PathVariable int id){
//        return productService.searchCategory(id);
//    }

    @GetMapping("{id}")
    public Product get(@PathVariable int id) {
        return productService.findById(id);
    }


    //admin
    //добавить товар в магазин
    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }
}
