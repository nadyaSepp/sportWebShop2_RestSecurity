package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(int id) {
        Optional<Product> result = productRepository.findById(id);
        return result.orElseThrow();
    }
    public Product create(Product product) {
        return productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }


    //find from category
//    public List<Product> searchCategory(int id) {
//        return productRepository.findDistinctByCategory(id);
//    }


    //search (товаров по заданным параметрам
    //        название, бренд, цена, категория товара, размер, цвет)

    //update (заменить ... размер)
    //List<User> findByAndSort(String lastname, Sort sort);
//    public Product sort(Product productLast, Product productNew) {
//        Optional<Product> result = productRepository.findById(String productLast, Product productNew);
//        return result.orElseThrow();
//        return productRepository.save(productNew);
//    }


}
