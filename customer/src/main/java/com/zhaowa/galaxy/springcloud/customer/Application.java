package com.zhaowa.galaxy.springcloud.customer;

import com.zhaowa.galaxy.springcloud.customer.loadbalance.CanaryRuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zhaowa"})
@EnableDiscoveryClient
@LoadBalancerClient(value = "service-a", configuration = CanaryRuleConfiguration.class)
@EnableFeignClients(basePackages = {"com.zhaowa.galaxy.springcloud"})
// 不要用 @EnableFeignClients
//@EnableReactiveFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
