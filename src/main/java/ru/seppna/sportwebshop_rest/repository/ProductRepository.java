package ru.seppna.sportwebshop_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.seppna.sportwebshop_rest.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
