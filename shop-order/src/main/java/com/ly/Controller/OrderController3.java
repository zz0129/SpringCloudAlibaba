package com.ly.Controller;

import com.ly.service.impl.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * 服务稳定性保障：
 * 1、流量限制，不被上游服务击垮（资源级别）
 * 2、熔断降级，不被下游服务拖垮（资源级别）
 * 3、系统负载保护（系统级别）
 */
/**
 * Hystrix使用的是线程池隔离，做到了资源的隔离，但同时增加了线程切换的成本
 * Sentinel是通过并发数量和响应时间来对资源做限制
 */

@Slf4j
@RestController
public class OrderController3 {

    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;

    @RequestMapping("/order/message")
    public String message(@PathParam("serviceName") String serviceName) {
        orderServiceImpl3.message();
        return "message test";
    }

    @RequestMapping("/order/message1")
    public String message1() {
        orderServiceImpl3.message();
        return "message1 test";
    }
}
