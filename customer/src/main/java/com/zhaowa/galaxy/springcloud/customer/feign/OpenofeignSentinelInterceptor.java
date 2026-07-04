package com.zhaowa.galaxy.springcloud.customer.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenofeignSentinelInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("SentinelSource", "customer");
    }
}
