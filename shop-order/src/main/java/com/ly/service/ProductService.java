package com.ly.service;

import com.ly.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-product")
public interface ProductService {

    @RequestMapping("/product/{pid}")
    Product findById(@PathVariable Integer pid);
}
