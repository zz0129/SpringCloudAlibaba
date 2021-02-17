package com.ly.Controller;


import com.ly.domain.Order;
import com.ly.domain.Product;
import com.ly.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
/*
@RestController
*/
public class OrderController2 {

    @Autowired
    private ProductService productService;

    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求，下面调用商品微服务查询商品信息");

        Product product = productService.findById(pid);
        log.info("查询到{}号商品信息，内容是：{}", pid, product.toString());

        /**
         * 模拟调用商品微服务需要2s的时间
         * JMeter进行并发测试
         */
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        log.info("创建订单成功，订单信息为：{}", order.toString());
        return order;
    }

    @RequestMapping("/order/message")
    public String message(){
        return "message test";
    }
}
