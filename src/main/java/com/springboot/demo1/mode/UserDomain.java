package com.springboot.demo1.mode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {

    private Integer userId;

    private String userName;

    private String password;

    private String phone;
}
