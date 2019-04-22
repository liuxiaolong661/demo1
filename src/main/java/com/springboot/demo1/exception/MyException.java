package com.springboot.demo1.exception;

import com.springboot.demo1.mode.ResultCode;

public class MyException extends RuntimeException {


    public MyException(ResultCode code, String msg) {
        super(msg);
    }

}
