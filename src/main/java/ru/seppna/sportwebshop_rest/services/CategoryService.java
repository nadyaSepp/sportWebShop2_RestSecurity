package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.Category;
import ru.seppna.sportwebshop_rest.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    //admin, user
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    //admin, user
    public Category findById(int id) {
        Optional<Category> result = categoryRepository.findById(id);
        return result.orElseThrow();
    }
//    public List<Category> findByPrefix(String prefix){
//        return categoryRepository.findByNameStartingWithIgnoreCase(prefix);
//    }

    //admin
    //update

    //admin
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    //admin
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}
