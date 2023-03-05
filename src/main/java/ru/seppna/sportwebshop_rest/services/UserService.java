package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.error.NoSuchProductException;
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

   //проверка email(регулярным выражением)
   public boolean controlEmail(User user){
        boolean matchesEmail=user.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if(matchesEmail==false){System.out.println(String.format("ERROR: ",user.getEmail()));}
        return matchesEmail;
    }

    public User create(User user) {
        boolean result=controlEmail(user);
        //проверка корректности email
        if (! result) { throw new EntityExistsException("Bad email"); }

        //проверка повтора email
        AtomicInteger flagEmail=new AtomicInteger(0);
        List<User> users=userRepository.findAll();
        //перебираем объект через бегунок - u
        users.forEach(u->{ if ( u.getEmail().equals(user.getEmail()) )
                                { flagEmail.set(1); }
                         }
                     );
        if (flagEmail.get() == 1) { throw new IllegalArgumentException("Exists email"); }

        user.setRegistration(new Date());
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
    public User updatePasswd(int id, User newUser) {
        User user=findById(id);
        //вставляем хеширование пароля
        user.setPasswd(hashPasswd(newUser));
        return userRepository.save(user);
    }
    public int delete(int id) {
        //сначало проверяем есть ли он?
        userRepository.findById(id).orElseThrow();
        //тогда удаляем
        userRepository.deleteById(id);
        return id;
    }

    //вставляем авторизацию (роль) от суперадмина
    public User setRoleUser(int id, Role role){
        //сначало проверяем есть ли он?
        User user=findById(id);
        //System.out.println(role);
        user.setRole(role);
        return userRepository.save(user);
    }

    //вставляем статус от суперадмина
    public User setStatusUser(int id, Status status) {
        //сначало проверяем есть ли он?
        User user=findById(id);
        user.setStatus(status);
        return userRepository.save(user);
    }

    //собираем покупку клиента
    @Transactional
    public Buy commitBuy(int id, List<Receipt> items) {
        //проверка usera
        User user = findById(id);
        //см.какие товары хочет купить и сколько
        items.forEach(receipt -> System.out.println("111 " + receipt.getProduct().getId() + ":" + receipt.getCount() + ":" + receipt.getBuy()));

       //проверяем, можно ли купить этот товар
        items.forEach(receipt -> { Optional<Product> p = productRepository.findById(receipt.getProduct().getId());
                                   if (p.get().getIspresence().equals("нет"))
                                     {throw new NoSuchProductException("Product not available");}
                                   else receipt.getProduct().setIspresence("есть");
                                 });
        //собираем чек(покупку)
        Buy buy = new Buy(user, new Date());

         //расчет суммы всей покупки
        buy.setPay(buy.sum(items));
        System.out.printf("Success sum: %f\n",buy.getPay());

        items.forEach(item -> item.setBuy(buy));

        buy.setReceipts(items);
        buyRepository.save(buy);
        System.out.println("Success buy: ");

        receiptRepository.saveAll(items);
        System.out.println("Success receipts: ");
        return buy;
    }

    //показать все покупки клиента с расчитанной суммой покупки!
    public List<Buy> allBuys(int id) {
        User user = findById(id);
        List<Buy> buys=user.getBuys();
        buys.forEach(b-> b.setPay(b.sum(b.getReceipts())));
        return buys;
    }


}
