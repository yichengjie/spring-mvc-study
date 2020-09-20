package com.yicj.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@Controller
public class HomeController {

    @RequestMapping("myCallableAsync")
    public Callable<String> myCallableAsync(){
        return () ->{
            simulateLongTimeProcess(5000l);
            System.out.println(1);
            return "defaultView" ;
        } ;
    }
    @RequestMapping("myDeferredAsync")
    public DeferredResult<String> myDeferredAsync(){
        DeferredResult<String> result = new DeferredResult<>() ;
        setResultAsync(result) ;
        return result ;
    }

    // 通过一个线程模拟异步设置结果
    private void setResultAsync(DeferredResult<String> result) {
        Runnable runnable = () ->{
            simulateLongTimeProcess(5000L);
            result.setResult("defaultView") ;
        } ;
        new Thread(runnable).start();
    }


    // 模拟长时间请求，参数为休眠毫秒数
    private void simulateLongTimeProcess(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
