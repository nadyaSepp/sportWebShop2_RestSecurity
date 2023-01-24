package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.repositories.BuyRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuyService {
    private final BuyRepository buyRepository;

    public List<Buy> findAll(){
        return buyRepository.findAll();
    }

    public Buy findById(int id) {
        Optional<Buy> result = buyRepository.findById(id);
        return result.orElseThrow();
    }

//    public List<Product> allProducts(){
//        return buyRepository.allProducts();
//    }

//    public  List<Buy> findDistinctByUseridAndRegistration(User user, Date value){
//        return buyRepository.findDistinctByUseridAndRegistration(user,value);
//    }
    public Buy create(Buy buy) {
        return buyRepository.save(buy);
    }

    public void delete(int id) {
        buyRepository.deleteById(id);
    }


}
