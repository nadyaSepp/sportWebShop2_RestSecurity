package ru.seppna.sportwebshop_rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//Категории товаров

@Data
@NoArgsConstructor
@Entity
@Table(name="categorys")
public class Category {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;

    @OneToMany(mappedBy = "category")
    @JsonIgnore //чтоб не было зацикливания JSON при сериализации (парсинге) сущностей
    private List<Product> products;
}
