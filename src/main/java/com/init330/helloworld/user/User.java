package com.init330.helloworld.hello;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    public static User create(
            String name
    ){
        User user = new User();
        user.name = name;
        return user;
    }
}
