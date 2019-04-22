package com.springboot.demo1.advice;

import com.springboot.demo1.exception.MyException;
import com.springboot.demo1.mode.Result;
import com.springboot.demo1.mode.ResultCode;
import com.springboot.demo1.mode.ResultGenerator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {

//    @ExceptionHandler(Exception.class)
//    public Result jsonErrorHandler(HttpServletRequest req, Exception e) {
//        return ResultGenerator.genFailResult(e.getMessage());
//    }


    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req) {
        String acceptHeader = req.getHeader("Accept");
        if (acceptHeader.contains("text/html")) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("msg", e.getMessage());
            modelAndView.addObject("url", req.getRequestURL());
            modelAndView.addObject("stackTrace", e.getStackTrace());
            modelAndView.setViewName("error/error");
            return modelAndView;
        } else {
            StackTraceElement stackTraceElement= e.getStackTrace()[0];
            String message = e.toString()+",errorMassage:"+stackTraceElement+","+"errorLine:"+stackTraceElement.getLineNumber();
            return ResultGenerator.genFailResult(ResultCode.INTERNAL_SERVER_ERROR, message);
        }
    }

    @ExceptionHandler(MyException .class)
    public Object handleMyException(MyException e, HttpServletRequest req) {
        return genFailResult(e, e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException .class)
    public Object handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest req) {
//        StackTraceElement stackTraceElement= e.getStackTrace()[0];
//        String message = e.toString()+",errorMassage:"+stackTraceElement+","+"errorLine:"+stackTraceElement.getLineNumber();
//        return ResultGenerator.genFailResult(ResultCode.INTERNAL_SERVER_ERROR, message);
        return genFailResult(e, "数据插入失败!");
    }

    public Result genFailResult(Exception e, String msg){
        StackTraceElement stackTraceElement= e.getStackTrace()[0];
//        String message = e.toString()+",errorMassage:"+stackTraceElement+","+"errorLine:"+stackTraceElement.getLineNumber();
        return ResultGenerator.genFailResult(ResultCode.INTERNAL_SERVER_ERROR, msg);
    }
}
