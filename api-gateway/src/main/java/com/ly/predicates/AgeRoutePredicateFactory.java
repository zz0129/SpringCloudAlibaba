package com.ly.predicates;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义路由断言工厂类，
 * 名字必须是：断言+RoutePredicateFactory
 * 必须继承AbstractRoutePredicateFactory
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    //读取配置文件中的参数值，将其赋值到参数类的属性中
    @Override
    public List<String> shortcutFieldOrder() {
        //顺序要和配置的顺序相一致
        return Arrays.asList("minAge", "maxAge");
    }

    @Override
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //接收传入的参数，判断是否为空及判断是否符合条件
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                if (StringUtils.isNotBlank(ageStr)) {
                    int age = Integer.parseInt(ageStr);
                    if (age > config.getMinAge() && age < config.getMaxAge()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
            }
        };
    }

    /**
     * 配置类，用于接收配置类的属性值
     */
    @Data
    public static class Config {
        private int minAge;
        private int maxAge;
    }
}
