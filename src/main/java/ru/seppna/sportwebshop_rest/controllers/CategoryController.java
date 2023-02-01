package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Category;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.services.CategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    //show все категории товаров
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping
    public List<Category> getAll(){
        return categoryService.findAll();
    }

    //найти категорию по id
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping("{id}")
    public Category get(@PathVariable int id) {
        return categoryService.findById(id);
    }

    //показать товары указанной категории
    @PreAuthorize("hasAuthority('product:read')")
    @GetMapping("/{id}/products")
    public List<Product> allProducts(@PathVariable int id){
        return categoryService.findById(id).getProducts();
    }

//-----
//    @PreAuthorize("hasAuthority('product:read')")
//    @GetMapping("/{id}/products")
//    public List<Product> allProducts(@PathVariable int id){
//        return categoryService.findById(id).getProducts();
//    }
////------

    //создать категорию
    @PostMapping
    @PreAuthorize("hasAuthority('product:write')")
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    //удалить категорию
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('product:write')")
    public void delete(@PathVariable int id) {
        categoryService.delete(id);
    }
}
