package com.springboot.demo1.controller;

import com.springboot.demo1.mode.Result;
import com.springboot.demo1.mode.ResultCode;
import com.springboot.demo1.mode.ResultGenerator;
import com.springboot.demo1.mode.UserDomain;
import com.springboot.demo1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("")
    public Result addUser(
            @RequestParam(value = "userName")
                    String userName,
            @RequestParam(value = "password")
                    String password,
            @RequestParam(value = "phone")
                    String phone
    ){
        logger.info("===========addUser========");

        if (StringUtils.isEmpty(userName)){
//            throw new MyException(ResultCode.FAIL, "userName is empty!");
            return ResultGenerator.genFailResult(ResultCode.UNAUTHORIZED, "userName is empty!");
        }
        UserDomain userDomain = new UserDomain();
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.insert(userDomain);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("")
    public ResponseEntity deleteUser(@RequestParam(value = "userId", required = true) Integer userId){
        logger.info("===========deleteUser========");
        int num = userService.deleteUserById(userId);
        if (num>0){
            return ResponseEntity.ok("删除成功");
        }else{
            return ResponseEntity.ok("删除失败");

        }
    }

    @PutMapping("")
    public ResponseEntity updateUser(
            @RequestParam(value = "userId", required = true)
                    Integer userId,
            @RequestParam(value = "userName", required = false)
                    String userName,
            @RequestParam(value = "password", required = false)
                    String password,
            @RequestParam(value = "phone", required = false)
                    String phone
    ){
        logger.info("===========updateUser========");
        UserDomain userDomain = new UserDomain();
        userDomain.setUserId(userId);
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.updateUser(userDomain);
        return ResponseEntity.ok("更新成功");
    }

    @GetMapping("")
    public ResponseEntity getUsers(){
        logger.info("===========getUsers========");
        return ResponseEntity.ok(userService.selectUsers());
    }

    @GetMapping("getall")
    public Result getAllUsers(){
        logger.info("===========getUsers========");
//        int i=1/0;
        return ResultGenerator.genSuccessResult(userService.selectUsers());
    }



}
