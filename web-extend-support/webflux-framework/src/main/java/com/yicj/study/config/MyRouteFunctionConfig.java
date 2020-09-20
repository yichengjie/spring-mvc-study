package com.yicj.study.config;

import com.yicj.study.handler.MyFunctionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MyRouteFunctionConfig {

    // 路由函数注册为Bean
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        // 创建处理器实例
        MyFunctionHandler handler = new MyFunctionHandler() ;
        // 注册路由函数，/echo的post方法路由到handler的echo方法
        // /get路径上带参数key的get方法路由到handler的get方法
        // /list路径上的get方法路由到handler的list方法
        RouterFunction<ServerResponse> router =
                route(POST("/echo"), handler::echo)
                .andRoute(GET("/get"), handler::get)
                .andRoute(GET("/list"), handler::list);
        return router ;
    }

}
