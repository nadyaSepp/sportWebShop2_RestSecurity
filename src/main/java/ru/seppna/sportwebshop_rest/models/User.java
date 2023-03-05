package ru.seppna.sportwebshop_rest.models;

//Сущность User - пользователь

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="firstname")
    private String firstname;
    @Column(name="surname")
    private String surname;
    @Column(name="login")
    private String login;
    @Column(name="passwd")
    private String passwd;
    @Column(name="registration")
    private Date registration;
    @Column(name="city")
    private String city;
    @Column(name="country")
    private String country;
    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Buy> buys;
}
