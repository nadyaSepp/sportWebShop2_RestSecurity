package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.Buy;
import ru.seppna.sportwebshop_rest.repository.BuyRepository;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class BuyService {
    private final BuyRepository buyRepository;

    public List<Buy> findAll(){
        List<Buy> buys=buyRepository.findAll();
        buys.forEach(b-> b.setPay(b.sum(b.getReceipts())));
        return buys;
    }

    public Buy findById(int id) {
        Optional<Buy> result = buyRepository.findById(id);
        if (result.isPresent()) {
            result.get().setPay(result.get().sum(result.get().getReceipts()));
        }
        return result.orElseThrow();
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
            //System.out.println(item.getRegistration().getTime()); //еще можно сравнивать в mlsek
            if (item.getRegistration().before(value)) {
                buysBefore.add(item);
            }
        }
        buysBefore.forEach(b-> b.setPay(b.sum(b.getReceipts())));
        return buysBefore;
    }

    //была ли проверяемая дата позже
    public List<Buy> findAfterDate(Date value) {
        List<Buy> buysAfter=new ArrayList<Buy>();
        List<Buy> buys=buyRepository.findAll();
        for (Buy item : buys) {
            if (item.getRegistration().after(value)) {buysAfter.add(item);}
        }
        //for (Buy item : buysAfter) {
        //double pay = sum(item.getReceipts());
        //item.setPay(pay);
        //}
        buysAfter.forEach(b-> b.setPay(b.sum(b.getReceipts())));
        return buysAfter;
    }

}
