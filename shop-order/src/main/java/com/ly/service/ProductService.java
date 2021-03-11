package com.ly.service;

import com.ly.domain.Product;
import com.ly.service.fallback.ProductServiceFallBackFactory;
import com.ly.service.fallback.ProductServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-product",
        fallback = ProductServiceFallback.class,
        fallbackFactory = ProductServiceFallBackFactory.class
)
public interface ProductService {

    @RequestMapping("/product/{pid}")
    Product findById(@PathVariable Integer pid);
}
