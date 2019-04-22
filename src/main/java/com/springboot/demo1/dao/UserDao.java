package com.springboot.demo1.dao;

import com.springboot.demo1.mode.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    int insert(UserDomain user);

    int deleteUserById(@Param("userId") Integer userId);

    void updateUser(UserDomain user);

    List<UserDomain> selectUser();
}
