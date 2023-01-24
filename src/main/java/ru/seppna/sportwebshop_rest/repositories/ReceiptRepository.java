package ru.seppna.sportwebshop_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seppna.sportwebshop_rest.models.Receipt;


public interface ReceiptRepository extends JpaRepository<Receipt,Integer> {

}
