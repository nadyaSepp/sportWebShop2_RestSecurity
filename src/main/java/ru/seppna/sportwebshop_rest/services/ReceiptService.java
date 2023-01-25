package ru.seppna.sportwebshop_rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seppna.sportwebshop_rest.models.Receipt;
import ru.seppna.sportwebshop_rest.repository.ReceiptRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    public List<Receipt> findAll(){
        return receiptRepository.findAll();
    }

    public Receipt findById(int id) {
        Optional<Receipt> result = receiptRepository.findById(id);
        return result.orElseThrow();
    }

    public Receipt create(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public void delete(int id) {
        receiptRepository.deleteById(id);
    }
}
