package ru.seppna.sportwebshop_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.seppna.sportwebshop_rest.models.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt,Integer> {

}
