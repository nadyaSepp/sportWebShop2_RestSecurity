package ru.seppna.sportwebshop_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.Receipt;

import java.util.Date;
import java.util.List;

@Repository
public interface BuyRepository extends JpaRepository<Buy,Integer> {
    List<Receipt> findByRegistration(Date value);

//    @Query(nativeQuery = true, value = "select * FROM products")
//    List<Product> allProducts();

    //поиск покупки по user(id) и дате покупки
    //List<Buy> findDistinctByUseridAndRegistration(User user, Date value);

}
