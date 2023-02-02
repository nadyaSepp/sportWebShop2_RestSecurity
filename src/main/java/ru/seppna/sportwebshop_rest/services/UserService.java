package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.Receipt;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.repository.BuyRepository;
import ru.seppna.sportwebshop_rest.repository.ProductRepository;
import ru.seppna.sportwebshop_rest.repository.ReceiptRepository;
import ru.seppna.sportwebshop_rest.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReceiptRepository receiptRepository;
    private final BuyRepository buyRepository;

    private final ProductRepository productRepository;

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

        //см.какие товары купил и сколько
        //items.forEach(receipt -> System.out.println(receipt.getProduct().getId() + ":" + receipt.getCount()));
        //расчет суммы всей покупки
        double sum = 0.;
        for (Receipt item : items) {
            Optional<Product> product=productRepository.findById(item.getProduct().getId());
            System.out.println(product.get().getPrice() + ":" + item.getCount());
            sum+=product.get().getPrice() * item.getCount();
        }
        buy.setPay(sum);
        System.out.printf("Success sum: %f\n",buy.getPay());

        items.forEach(item -> item.setBuy(buy));
        buyRepository.save(buy);

        System.out.println("Success buy: ");
        receiptRepository.saveAll(items);
        return buy;
    }
}
