package com.yicj.study.config;

import com.yicj.study.controller.controllersupport.MyHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Collections;

@Configuration
public class MyConfig {

    @Autowired
    private MyHttpRequestHandler myHttpRequestHandler ;

    @Bean
    public SimpleUrlHandlerMapping simpleUrlHandlerMapping(){
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping() ;
        //设置高优先级,因为别的handlerMapper可能匹配到这个地址
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        mapping.setUrlMap(Collections.singletonMap("httpRequestHandler", myHttpRequestHandler));
        return mapping ;
    }
}
