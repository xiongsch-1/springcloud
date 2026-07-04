package com.zhaowa.galaxy.springcloud.servicea;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SentinelOriginParser implements RequestOriginParser {
    public static final Logger logger = LoggerFactory.getLogger(SentinelOriginParser.class);

    @Override
    public String parseOrigin(HttpServletRequest request) {
        logger.info("request {}, header={}", request.getParameterMap().get("param"), request.getHeader("SentinelSource"));
        return request.getHeader("SentinelSource");
    }
}
