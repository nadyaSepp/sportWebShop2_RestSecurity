package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.Receipt;
import ru.seppna.sportwebshop_rest.repository.BuyRepository;
import ru.seppna.sportwebshop_rest.repository.ProductRepository;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuyService {
    private final BuyRepository buyRepository;
    private final ProductRepository productRepository;

    //метод расчета всей суммы покупки
    public double sum(List<Receipt> items) {
        //расчет суммы всей покупки
        double sum = 0.0;
        for (Receipt item : items) {
            Optional<Product> product = productRepository.findById(item.getProduct().getId());
            System.out.println(product.get().getPrice() + ":" + item.getCount());
            sum += product.get().getPrice() * item.getCount();
        }
        return sum;
    }

    public List<Buy> findAll(){
        List<Buy> buys=buyRepository.findAll();
        for (Buy item : buys) {
            Optional<Buy> result = buyRepository.findById(item.getId());
            if (! result.isEmpty()) {
                double pay = sum(result.get().getReceipts());
                //System.out.println(pay);
                result.get().setPay(pay);
            }
        }
        //return buyRepository.findAll();
        return buys;
    }

    public Buy findById(int id) {
        Optional<Buy> result = buyRepository.findById(id);
        //---02.02 расчет всей суммы покупки
        if (! result.isEmpty()) {
            double pay = sum(result.get().getReceipts());
            System.out.println(pay);
            result.get().setPay(pay);
        }
        //-----------------------
    return result.orElseThrow();
    }

    public Buy create(Buy buy) {
        return buyRepository.save(buy);
    }

    public List<Receipt> findBeforeDate(Date value) {
        System.out.println(value.getTime());
        //System.out.println(value.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"))));
        //if ()
        return buyRepository.findByRegistration(value);
    }

//    public List<Product> allProducts(){
//       return buyRepository.allProducts();
//    }

}
