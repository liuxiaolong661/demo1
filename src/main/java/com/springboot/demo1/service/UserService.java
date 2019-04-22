package com.springboot.demo1.service;

import com.springboot.demo1.mode.UserDomain;

import java.util.List;

public interface UserService {

    int insert(UserDomain record);

    int deleteUserById(Integer userId);

    void updateUser(UserDomain userDomain);

    List<UserDomain> selectUsers();
}
