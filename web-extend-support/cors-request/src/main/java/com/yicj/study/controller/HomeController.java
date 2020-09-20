package com.yicj.study.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HomeController {
    // 只允许Origin是https://www.baidu.com的跨域请求
    //@CrossOrigin(origins = "https://www.baidu.com", allowCredentials = "true", exposedHeaders = "Content-type")
    // 允许所有的跨域访问
    @CrossOrigin(origins = "*", allowCredentials = "true", exposedHeaders = "Content-type")
    @GetMapping("/myCors")
    public String myCors(){
        return "CORS TEST" ;
    }


    @CrossOrigin(origins = "https://www.baidu.com",
            allowCredentials = "true",
            allowedHeaders = {"X-Custom-Header","X-Other-Header"})
    @PutMapping("/myComplexCors")
    public String myComplexCors(){
        return "CORS TEST" ;
    }


    @PutMapping("myComplexCors2")
    public String myComplexCors2(){
        return "CORS TEST" ;
    }

    @GetMapping("/cookie")
    public String cookie(HttpServletResponse response){
        Cookie cookie = new Cookie("testcookies1", "helloworld") ;
        cookie.setDomain("www.baidu.com");
        response.addCookie(cookie);
        return "success" ;
    }
}
