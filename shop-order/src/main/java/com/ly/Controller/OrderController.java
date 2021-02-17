package com.ly.Controller;

import com.ly.domain.Order;
import com.ly.domain.Product;
import com.ly.service.OrderService;
import com.ly.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Slf4j
/*
@RestController
*/
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private DiscoveryClient discoveryClient;

    //普通下单请求
    /*@RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {

        *//**
         * 直接调用存在的问题
         * 1、如果被调用方提供的地址变化了 我们就必须要修改代码
         * 2、如果被调用方使用了集群的方式 没有办法做到负载均衡
         * 3、微服务越来越多以后 维护地址麻烦
         *//*
        log.info("接收到{}号商品的下单请求，下面调用商品微服务查询商品信息");
        Product product = restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);
        log.info("查询到{}号商品信息，内容是：{}", pid, product.toString());

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.createOrder(order);

        log.info("创建订单成功，订单信息为：{}", order.toString());
        return order;
    }*/

    //使用nacos进行服务治理,自定义负载均衡
    /*@RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求，下面调用商品微服务查询商品信息");

        List<ServiceInstance> list = discoveryClient.getInstances("service-product");
        int index = new Random().nextInt(list.size());
        ServiceInstance productService = list.get(index);
        Product product = restTemplate.getForObject("http://" + productService.getHost() + ":" + productService.getPort() + "/product/" + pid, Product.class);
        log.info("查询到{}号商品信息，内容是：{}", pid, product.toString());

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.createOrder(order);

        log.info("创建订单成功，订单信息为：{}", order.toString());
        return order;
    }*/

    //Ribbon实现负载均衡、RestTemplate
/*    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求，下面调用商品微服务查询商品信息");

        Product product = restTemplate.getForObject("http://service-product/product/" + pid, Product.class);
        log.info("查询到{}号商品信息，内容是：{}", pid, product.toString());

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.createOrder(order);

        log.info("创建订单成功，订单信息为：{}", order.toString());
        return order;
    }*/


    //feign
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求，下面调用商品微服务查询商品信息");

        Product product = productService.findById(pid);
        log.info("查询到{}号商品信息，内容是：{}", pid, product.toString());

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.createOrder(order);

        log.info("创建订单成功，订单信息为：{}", order.toString());
        return order;
    }
}
