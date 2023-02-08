package ru.seppna.sportwebshop_rest.models;

//Таблица связи - количество позиций в покупке Buy (чеке)

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="receipts")
public class Receipt {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="count")
    private int count;

    @ManyToOne
    @JoinColumn(name="buyid",referencedColumnName = "id")
    @JsonIgnore
    private Buy buy;

    @ManyToOne
    @JoinColumn(name="productid",referencedColumnName = "id")
    private Product product;
}
