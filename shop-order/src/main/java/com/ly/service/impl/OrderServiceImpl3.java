package com.ly.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ly.service.OrderServiceImpl3BlockHandler;
import com.ly.service.OrderServiceImpl3Fallback;
import org.springframework.stereotype.Service;

/**
 * 资源可以针对某个链路来进行流控
 */
@Service
public class OrderServiceImpl3 {

    @SentinelResource(value = "message",
            blockHandler = "blockHandler", //Sentinel相关异常
            blockHandlerClass = OrderServiceImpl3BlockHandler.class,
            fallback = "fallback",   //Throwable相关异常
            fallbackClass = OrderServiceImpl3Fallback.class)
    public String message(){
        return "message";
    }
}
