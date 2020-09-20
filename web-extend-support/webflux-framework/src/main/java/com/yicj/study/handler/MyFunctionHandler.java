package com.yicj.study.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class MyFunctionHandler {

    // 返回请求体内容
    public Mono<ServerResponse> echo(ServerRequest request){
        return ServerResponse.ok().body(request.bodyToMono(String.class), String.class) ;
    }

    //返回请求参数key内容
    public Mono<ServerResponse> get(ServerRequest request){
        return ServerResponse.ok().body(fromObject(request.queryParam("key"))) ;
    }

    // 返回所有请求头的名称列表
    public Mono<ServerResponse> list(ServerRequest request){
        return ServerResponse.ok().body(fromObject(request.headers().asHttpHeaders().keySet())) ;
    }
}
