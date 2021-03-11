package com.ly.service.fallback;

import com.ly.domain.Product;
import com.ly.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product findById(Integer pid) {
                log.error("{}",throwable);
                Product product = new Product();
                product.setPid(-100);
                product.setPname("商品微服务调用出现异常了,已经进入到了容错方法中");
                return product;
            }
        };
    }
}
