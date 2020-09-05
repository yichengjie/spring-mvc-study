package com.yicj.study.controller.modelsupport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ModelSupportController {

    @GetMapping("/forwardView")
    public String forwardView(Model model){
        // 设置转发前的模型属性
        model.addAttribute("info", "转发前的属性") ;
        //返回转发视图，转发目标是forwardTargetView
        return  "forward:forwardTargetView" ;
    }

    @GetMapping("/forwardTargetView")
    public String forwardTargetView(Model model, HttpServletRequest request){
        Object info = request.getAttribute("info");
        // 设置模型属性
        model.addAttribute("first", info) ;
        model.addAttribute("second", "转发后的属性") ;
        return "viewView" ;
    }



    @GetMapping("/redirectView")
    public String redirectView(RedirectAttributes model){
        // 重定向前的模型数据
        model.addFlashAttribute("first", "重定向前的属性") ;
        // 返回重定向视图，转发目标forwardTargetView
        return "redirect:redirectTargetView" ;
    }

    @GetMapping("/redirectTargetView")
    public String redirectTargetView(Model model){
        //此时的Model已经有了重定向的属性了
        model.addAttribute("second", "重定向后的属性") ;
        return "viewView" ;
    }

}
