package ru.seppna.sportwebshop_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.seppna.sportwebshop_rest.models.Buy;

@Repository
public interface BuyRepository extends JpaRepository<Buy,Integer> {
    //@Query(nativeQuery = true, value = "select * FROM products")
    //List<Product> allProducts();


    //поиск покупки по user(id) и дате покупки
    //List<Buy> findDistinctByUseridAndRegistration(User user, Date value);

}
