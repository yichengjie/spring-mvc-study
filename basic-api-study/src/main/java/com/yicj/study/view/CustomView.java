package com.yicj.study.view;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

//该解析方式是通过BeanNameViewResolver执行的，这种方式要求申请的Bean类型为View接口的实现
@Component("beanNameViewBean")
public class CustomView implements View {

    //返回该View支持text/html类型的ContentType,用于查找到多个View时选择最优匹配结果
    @Override
    public String getContentType() {
        return MediaType.TEXT_HTML_VALUE;
    }

    // 执行视图的渲染操作,第一个参数是处理器逻辑中Model处理逻辑产生的Model模型数量
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //从Model中拿到模型数据
        Object name = model.get("name");
        //返回页面内容
        response.getWriter().append("name is " + name) ;
    }
}
