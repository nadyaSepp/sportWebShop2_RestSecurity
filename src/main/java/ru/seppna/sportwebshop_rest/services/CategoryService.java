package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.Category;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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
        //проверка повтора
        AtomicInteger flagEmail=new AtomicInteger(0);
        List<Category> items=categoryRepository.findAll();
        //берем объект через бегунок - u
        items.forEach(u->{ if (Objects.equals(u.getTitle(), category.getTitle()))
                { flagEmail.set(1); }
                }
        );
        if (flagEmail.get() == 1) { throw new IllegalArgumentException("Exists Category!"); }
        return categoryRepository.save(category);
    }


    public Category update(int id, String title) {
        Category category=categoryRepository.findById(id).orElseThrow();
        System.out.println(category.getTitle());
        category.setTitle(title);
        return categoryRepository.save(category);
    }
}
