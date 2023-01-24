package ru.seppna.sportwebshop_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.User;


import java.util.Date;
import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Integer> {
//    @Query(nativeQuery = true, value = "select * FROM products")
//    List<Product> allProducts();
//

    //поиск покупки по user(id) и дате покупки
//    List<Buy> findDistinctByUseridAndRegistration(User user, Date value);
}
