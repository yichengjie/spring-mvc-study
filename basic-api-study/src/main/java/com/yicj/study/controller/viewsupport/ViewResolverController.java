package com.yicj.study.controller.viewsupport;


import com.yicj.study.view.CustomView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

// 视图查找解析
@Controller
public class ViewResolverController {

    //该解析方式是通过BeanNameViewResolver执行的，这种方式要求申请的Bean类型为View接口的实现
    @GetMapping("/beanNameView")
    public String beanNameView(Model model){
        //添加一个Model属性
        model.addAttribute("name", "BeanNameView") ;
        // 返回ViewName用于查找
        return "beanNameViewBean" ;
    }


    //直接返回视图
    @RequestMapping("/returnView")
    public View returnView(Model model){
        //添加一个Model属性
        model.addAttribute("name", "returnView") ;
        // 直接返回视图
        return new CustomView() ;
    }

}
