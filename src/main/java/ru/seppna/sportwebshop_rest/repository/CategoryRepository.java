package ru.seppna.sportwebshop_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.seppna.sportwebshop_rest.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
