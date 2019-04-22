package com.springboot.demo1.service.impl;

import com.springboot.demo1.dao.UserDao;
import com.springboot.demo1.mode.UserDomain;
import com.springboot.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;//这里会爆红，请忽略

    @Override
    public int insert(UserDomain record) {
        int i=userDao.insert(record);
        if(i<1){
            throw new DataIntegrityViolationException("添加数据失败");
        }
        return 1;
    }

    @Override
    public int deleteUserById(Integer userId) {
        return userDao.deleteUserById(userId);
    }

    @Override
    public void updateUser(UserDomain userDomain) {
        userDao.updateUser(userDomain);
    }

    @Override
    public List<UserDomain> selectUsers() {
//        int i=1/0;
        return userDao.selectUser();
    }
}
