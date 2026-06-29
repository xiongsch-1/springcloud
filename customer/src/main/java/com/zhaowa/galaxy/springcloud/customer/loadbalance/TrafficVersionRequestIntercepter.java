package com.zhaowa.galaxy.springcloud.customer.loadbalance;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import static com.zhaowa.galaxy.springcloud.customer.constant.Constant.TRAFFIC_VERSION;

@Component
public class TrafficVersionRequestIntercepter implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // 从当前请求中获取流量标记并传递到下游
        String trafficVersion = TrafficContext.getTrafficVersion();
        if (trafficVersion != null && !trafficVersion.isEmpty()) {
            template.header(TRAFFIC_VERSION, trafficVersion);
            TrafficContext.clear();
        }

    }

}
