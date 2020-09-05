package com.yicj.study.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Map<String,String> map){
        map.put("name", "Map类型的Model") ;
        return "hello world" ;
    }

    @GetMapping("/hello2")
    public String hello2(Model model){
        model.addAttribute("name", "Map类型的Model") ;
        return "hello world2" ;
    }
}
