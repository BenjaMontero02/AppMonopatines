package com.ScootersApp.domain;

import com.ScootersApp.Service.DTOs.User.request.UserRequest;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "role", nullable = false)
    private String role;

    public User(UserRequest newUser){
        this.name = newUser.getName();
        this.surname = newUser.getSurname();
        this.mail = newUser.getMail();
        this.password = newUser.getPassword();
        this.phoneNumber = newUser.getPhoneNumber();
        this.role = newUser.getRole();
    }

    public User(String name, String surname, String mail, String password, String phoneNumber, String role) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
