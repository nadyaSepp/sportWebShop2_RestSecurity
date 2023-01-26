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

    @GetMapping
    public List<Category> getAll(){
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    public Category get(@PathVariable int id) {
        return categoryService.findById(id);
    }

//    @GetMapping("/prefix")
//    public List<Category> getByPrefix(@RequestParam String value){
//        return categoryService.findByPrefix(value);
//    }

    @GetMapping("/{id}/products")
    public List<Product> allProducts(@PathVariable int id){
        return categoryService.findById(id).getProducts();
    }


    @PostMapping
    @PreAuthorize("hasAuthority('product:write')")
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('product:write')")
    public void delete(@PathVariable int id) {
        categoryService.delete(id);
    }
}
