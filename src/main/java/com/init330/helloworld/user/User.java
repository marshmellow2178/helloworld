package com.init330.helloworld.user;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
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

    public void update(String name){
        this.name = name;
    }
}
