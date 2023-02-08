package ru.seppna.sportwebshop_rest.models;

//Сущность Product - товары магазина

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @Column(name="id")
    //IDENTITY -новое значение генерируется в зависимости от предыдущего
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="brand")
    private String brand;
    @Column(name="price")
    private double price;
    @Column(name="size")
    private double size;
    @Column(name="characteristic")
    private String characteristic;
    @Column(name="color")
    private String color;
    @Column(name="ispresence")
    private String ispresence;
    @ManyToOne
    @JoinColumn(name="categoryid",referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Receipt> receipts;
}
