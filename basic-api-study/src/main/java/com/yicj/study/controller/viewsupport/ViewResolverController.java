package com.yicj.study.controller.viewsupport;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 视图查找解析
@Controller
public class ViewResolverController {

    @GetMapping("/beanNameView")
    public String beanNameView(Model model){
        //添加一个Model属性
        model.addAttribute("name", "BeanNameView") ;
        // 返回ViewName用于查找
        return "beanNameViewBean" ;
    }



}
