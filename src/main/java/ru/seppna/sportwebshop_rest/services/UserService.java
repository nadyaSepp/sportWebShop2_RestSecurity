package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.*;
import ru.seppna.sportwebshop_rest.repository.BuyRepository;
import ru.seppna.sportwebshop_rest.repository.ProductRepository;
import ru.seppna.sportwebshop_rest.repository.ReceiptRepository;
import ru.seppna.sportwebshop_rest.repository.UserRepository;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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

    //хеширование пароля
   public String hashPasswd(User user){
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       String encodedPassword = passwordEncoder.encode(user.getPasswd());
       return  encodedPassword;
   }

   //проверка email(совпадение с регулярным выражением)
   public boolean matchesEmail(User user){
        boolean matchesEmail=user.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if(matchesEmail==false){System.out.println(String.format("ERROR: ",user.getEmail()));}
        return matchesEmail;
    }

    public User create(User user) {
        boolean result=matchesEmail(user);
        //проверка корректности email
        if (! result) { throw new EntityExistsException("Bad email"); }

        //проверка повтора email
        AtomicInteger flagEmail=new AtomicInteger(0);
        List<User> users=userRepository.findAll();
        //берем объект через бегунок - u
        users.forEach(u->{ if ( u.getEmail().equals(user.getEmail()) )
                                { flagEmail.set(1); }
                         }
                     );
        if (flagEmail.get() == 1) { throw new IllegalArgumentException("Exists email"); }

        //вставляем первичную авторизацию
        user.setRole(Role.CLIENT);
        user.setStatus(Status.ACTIVE);

        //вставляем хеширование пароля
        user.setPasswd(hashPasswd(user));
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user); }

    public User update(int id, User newUser){
        User user=findById(id);
        user.setFirstname(newUser.getFirstname());
        user.setSurname(newUser.getSurname());
        user.setCity(newUser.getCity());
        user.setCountry(newUser.getCountry());
        user.setPhone(newUser.getPhone());
        return userRepository.save(user);
    }

    public int delete(int id) {
        //сначало проверяем есть ли он?
        userRepository.findById(id).orElseThrow();
        //тогда удаляем
        userRepository.deleteById(id);
        return id;
    }

    public User setRoleUser(int id, Role role){
        //сначало проверяем есть ли он?
        User user=findById(id);
        System.out.println(role);
        user.setRole(role);
        return userRepository.save(user);
    }

    public User setStatusUser(int id, Status status) {
        //сначало проверяем есть ли он?
        User user=findById(id);
        user.setStatus(status);
        return userRepository.save(user);
    }
    @Transactional
    public Buy commitBuy(int id, List<Receipt> items) {
        User user = findById(id);
        Buy buy = new Buy(user, new Date());
        ///buy.setReceipts(items);

        //см.какие товары купил и сколько
        //items.forEach(receipt -> System.out.println("111 " + receipt.getProduct().getId() + ":" + receipt.getCount() + ":" + receipt.getBuy()));
        //расчет суммы всей покупки
        double sum = 0.;
        for (Receipt item : items) {
            Optional<Product> product=productRepository.findById(item.getProduct().getId());
            //System.out.println("222 " + product.get().getPrice() + ":" + item.getCount());
            sum+=product.get().getPrice() * item.getCount();
        }
        buy.setPay(sum);
        System.out.printf("Success sum: %f\n",buy.getPay());

        items.forEach(item -> item.setBuy(buy));

        buy.setReceipts(items);//убрала сверху
        buyRepository.save(buy);
        System.out.println("Success buy: ");

        receiptRepository.saveAll(items);
        System.out.println("Success receipts: ");
        return buy;
    }
}
