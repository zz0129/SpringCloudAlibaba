package com.ly.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/*
@Component
*/
public class RequestOriginParserDefinition implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String serviceName = httpServletRequest.getParameter("serviceName");
        StringBuffer url = httpServletRequest.getRequestURL();
        if (url.toString().endsWith("favicon.ico")) {
            // 浏览器会向后台请求favicon.ico图标
            return serviceName;
        }

        if (StringUtils.isEmpty(serviceName)) {
            throw new IllegalArgumentException("serviceName must not be null");
        }

        return serviceName;
    }
}
