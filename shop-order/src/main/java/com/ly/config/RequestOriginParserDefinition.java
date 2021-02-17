package com.ly.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class RequestOriginParserDefinition implements RequestOriginParser {

    /**
     * 返回值将交给流控应用进行配置
     */
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String serviceName = httpServletRequest.getParameter("serviceName");
        log.info("StringUtils.isBlank(serviceName) : {}, serviceName : {}", StringUtils.isBlank(serviceName), serviceName);
        if(StringUtils.isBlank(serviceName)) {
            throw new RuntimeException("serviceName is blank");
        }
        return serviceName;
    }
}
