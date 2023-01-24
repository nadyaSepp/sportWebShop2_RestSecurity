package ru.seppna.sportwebshop_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seppna.sportwebshop_rest.models.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
