package ru.seppna.sportwebshop_rest.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

//Сущность-покупки всех Users

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name="buys")
public class Buy {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="registration")
    private Date registration;

    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName = "id")
    private User user;

    //массив позиций покупки в чеке
    @OneToMany(mappedBy = "buy")
    //не ставим @JsonIgnore !!!!!
    private List<Receipt> receipts;

}
