package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.error.NoSuchProductException;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(int id) {
        Optional<Product> result = productRepository.findById(id);
        return result.orElseThrow(
                () -> new NoSuchProductException("No such product!")
        );
    }
    public Product create(Product product) {
        System.out.println(product.getTitle());
        return productRepository.save(product);
    }

    public void delete(int id) {
        //сначало проверяем есть ли он?
        productRepository.findById(id).orElseThrow();
        //тогда удаляем
        productRepository.deleteById(id);
    }

    public Product setPresence(int id, String presence) {
        //сначало проверяем есть ли он?
        Product product=productRepository.findById(id).orElseThrow();
        System.out.println(product.getTitle());
        product.setIspresence(presence);
        return productRepository.save(product);
    }

    public List<Product> search(String category,String brand, Double price, Double size) {
        List<Product> products=findAll();
        List<Product> productsSearch=products.stream()
                    .filter(f -> f.getCategory().getTitle().equals(category))
                    .filter(f -> f.getPrice() <= price)
                    .filter(f -> f.getBrand().equals(brand))
                    .filter(f -> f.getSize() == size)
                    .collect(Collectors.toList());
        if (productsSearch.size() == 0) {throw new NoSuchProductException("Sorry, product is absent!");}
        return productsSearch;
    }

}
