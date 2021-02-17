package com.ly.controller;

import com.ly.domain.Product;
import com.ly.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //商品查询
    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid) {
        log.info("接下来要进行{}号商品的查询", pid);
        Product product = productService.findByPid(pid);
        log.info("商品信息查询成功，内容为：{}", product.toString());
        return product;
    }
}
