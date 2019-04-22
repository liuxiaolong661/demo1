package com.springboot.demo1.mode;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;
    private String nickname;
    private int age;

    public User(String username, String nickname){
        this.username = username;
        this.nickname = nickname;
    }

}
