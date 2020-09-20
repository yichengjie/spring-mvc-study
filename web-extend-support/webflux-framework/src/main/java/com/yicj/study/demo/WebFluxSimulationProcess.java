package com.yicj.study.demo;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

// webflux模拟过程
public class WebFluxSimulationProcess {

    public static void main(String[] args) {
        simulateRequestAndResponse();
    }

    private static void simulateRequestAndResponse(){
        Mono.fromFuture(delayRequest())
                .map(request -> "Hello " + request)
                .flatMap(request -> asyncResult(request))
                .subscribe(response -> System.out.println("response : " + response)) ;
        System.out.println("请求线程执行完成");
        sleep(20000L);
    }

    // 模拟一个获取请求的情况，从申请获取到获取结果需要5秒
    private static CompletableFuture<String> delayRequest(){
        return CompletableFuture.supplyAsync(()-> {
            sleep(5000);
            return "Request" ;
        }) ;
    }

    private static Mono<String> asyncResult(String request){
        System.out.println("request : " + request);
        return Mono.just("defaultView").delaySubscription(Duration.ofSeconds(5L)).log() ;
    }

    private static void sleep(long time){
        try {
            Thread.sleep(time) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
