package com.springboot.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
//@EnableAutoConfiguration
public class HelloController {


    @RequestMapping("index")
    private String index() {
        int i = 1 / 0;
        return "index111";
    }

    @RequestMapping("upload")
    private String upload() {
        return "upload";
    }

    @RequestMapping("map")
    @ResponseBody
    private Map<String, String> index1() {
        Map map = new HashMap<String, String>();
        map.put("北京", "北京市");
        map.put("湖北", "武汉市");
        map.put("广东", "深圳市");
//        map.put("guangxi", "guilin");

        return map;
    }


}
