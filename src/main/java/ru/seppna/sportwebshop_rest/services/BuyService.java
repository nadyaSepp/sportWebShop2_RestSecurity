package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.seppna.sportwebshop_rest.error.NoSuchProductException;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.models.Product;
import ru.seppna.sportwebshop_rest.models.Receipt;
import ru.seppna.sportwebshop_rest.repository.BuyRepository;
import ru.seppna.sportwebshop_rest.repository.ProductRepository;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class BuyService {
    private final BuyRepository buyRepository;
    private final ProductRepository productRepository;


    //метод расчета всей суммы покупки
    public double sum(List<Receipt> items) {
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
        return buys;
    }

    public Buy findById(int id) {
        Optional<Buy> result = buyRepository.findById(id);

        //расчет всей суммы покупки
        if (! result.isEmpty()) {
            double pay = sum(result.get().getReceipts());
            //System.out.println(pay);
            result.get().setPay(pay);
        }
        //return result.get();
        return result.orElseThrow();///07.02.23
    }

    public Buy create(Buy buy) {
        return buyRepository.save(buy);
    }

    //была ли проверяемая дата раньше
    public List<Buy> findBeforeDate(Date value) {
        //System.out.println(value.getTime());
        List<Buy> buysBefore=new ArrayList<Buy>();
        List<Buy> buys=buyRepository.findAll();
        for (Buy item : buys) {
            //System.out.println(item.getRegistration().getTime());
            if (item.getRegistration().before(value)) {
                buysBefore.add(item);
            }
        }
        for (Buy item : buysBefore) {
            double pay = sum(item.getReceipts());
            item.setPay(pay);
        }
        return buysBefore;
    }

    //была ли проверяемая дата позже
    public List<Buy> findAfterDate(Date value) {
        List<Buy> buysAfter=new ArrayList<Buy>();
        List<Buy> buys=buyRepository.findAll();
        for (Buy item : buys) {
            if (item.getRegistration().after(value)) {buysAfter.add(item);}
        }
        return buysAfter;
    }

}
