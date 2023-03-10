package ru.seppna.sportwebshop_rest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

//Сущность-покупка(чек) клиента

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="buys")
public class Buy {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="registration")
    private Date registration;

    //@Transient - помечаем вычисляемое поле, которого нет в таблице
    @Transient
    private double pay; //общая сумма покупки

    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName = "id")
    private User user;

    //массив позиций покупки в чеке
    @OneToMany(mappedBy = "buy",cascade = CascadeType.ALL)
    //не ставим @JsonIgnore !!!!!
    private List<Receipt> receipts;

    public Buy(int id, Date registration, int userId, List<Receipt> receipts) {
    }

    public Buy(int user, Date registration,List<Receipt> receipts) {
    }

    public Buy(User user, Date date) {
        this.user = user;
        this.registration = date;
    }

    //расчет суммы чека(покупки buy) за все товары
    public double sum(List<Receipt> items) {
        double sum = 0.0;
        for (Receipt item : items) {
            System.out.println(item.getProduct().getPrice() + ":" + item.getCount());
            sum += item.getProduct().getPrice() * item.getCount();
        }
        System.out.println("sum:" + sum);
        return sum;
    }

}
