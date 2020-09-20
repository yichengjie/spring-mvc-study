package com.yicj.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Spring mvc提供了一种特殊的配置，可以根据路径模式应用cors配置
 * 通过申明WebMvcConfigurer接口的实现类，作为Web MVC的配置类，可以对Web MVC经行配置
 * 通过实现接口中的addCorsMappings方法可以对CORS跟进路径模式进行配置
 */
@Configuration
public class MyCorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //addMapping参数为路径模式，其他方法与注解中参数相同
        registry.addMapping("/myComplexCors2")
                .allowedOrigins("https://www.baidu.com")
                .allowedHeaders("X-Custom-Header","X-Other-Header")
                .allowCredentials(true) ;

    }
}
