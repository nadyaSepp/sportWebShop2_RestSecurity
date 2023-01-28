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

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(int id) {
        Optional<Category> result = categoryRepository.findById(id);
        return result.orElseThrow();
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}
