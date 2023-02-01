package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Receipt;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.repository.BuyRepository;
import ru.seppna.sportwebshop_rest.repository.ReceiptRepository;
import ru.seppna.sportwebshop_rest.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReceiptRepository receiptRepository;
    private final BuyRepository buyRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow();
   }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public Buy commitBuy(int id, List<Receipt> items) {
        User user = findById(id);
        Buy buy = new Buy(user, new Date());
        buy.setReceipts(items);

//        double cost = items.stream()
//            .filter(f -> f.getCount() > 0)
//            .mapToDouble((f)-> Double.parseDouble(f.getProduct().getPrice())*f.getCount())
//            .sum();
//        buy.setPay(cost);

        items.forEach(item -> item.setBuy(buy));
        buyRepository.save(buy);
        System.out.printf("Success: %f",buy.getPay());
        System.out.println("Success: ");
        receiptRepository.saveAll(items);
        return buy;
    }
}
