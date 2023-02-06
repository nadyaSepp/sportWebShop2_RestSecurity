package ru.seppna.sportwebshop_rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.seppna.sportwebshop_rest.error.ErrorMessage;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Category;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.services.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    //обработка иск.ситуаций (тип 2 - свои сообщения ErrorMessage)
    // с указанием статуса в Header: HttpStatus.NOT_FOUND и сообщения "No value present!")
    // с указанием в ответе- "404 NOT FOUND"
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND) //чтоб статус ответа тоже остался ошибкой -"404 NOT FOUND"
    public ErrorMessage handle(NoSuchElementException ex){
        log.error(ex.getMessage());
        return  new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

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
    @PostMapping("/create")
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
