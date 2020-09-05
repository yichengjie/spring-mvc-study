package com.yicj.study.controller.viewsupport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

//视图类型包括thymeleaf,freemarker等
@Controller
public class ViewTypeController {

    @GetMapping("/thymeleafView")
    public String thymeleafView(Model model){
        // 为Mode添加属性
        model.addAttribute("name","闪光") ;
        model.addAttribute("sex",1) ;
        List<String> hobbies = new ArrayList<>() ;
        hobbies.add("计算机") ;
        hobbies.add("数学") ;
        hobbies.add("游戏") ;
        // 添加List类型的属性
        model.addAttribute("hobbies", hobbies) ;
        return "thymeleafView" ;
    }
}
