package com.springboot.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String filePath = "D://data/";

    @RequestMapping("upload")
    public JSONObject upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String name = request.getParameter("name");
        System.out.println("姓名：" + name);

        String fileName = file.getOriginalFilename();
        System.out.println("文件名：" + fileName);

        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("文件后缀：" + suffixName);


        String newFileName = UUID.randomUUID().toString() + suffixName;
        System.out.println("新文件名：" + newFileName);

        File dest = new File(filePath + newFileName);
        Map map = new HashMap();
        map.put("filePath", dest.getAbsolutePath());
        map.put("name", name);
        try {
            file.transferTo(dest);
            result.put("success", true);
            result.put("data", map);
            return result;
        }catch (Exception e){
            logger.error("upload error" + e);
            result.put("success", false);
        }
        return result;
    }
}
