package ru.seppna.sportwebshop_rest.models;

//База клиентов (покупатели и админы)

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name="isblock")
    private int isBlock;
    @Column(name="isadmin")
    private int isAdmin;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Buy> buys;
}
