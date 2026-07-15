package com.zhaowa.galaxy.springcloud.customer.event;

import com.zhaowa.galaxy.springcloud.customer.beans.RequestCustomer;
import com.zhaowa.galaxy.springcloud.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class CustomerConsumer {

    private static final Logger log = LoggerFactory.getLogger(CustomerConsumer.class);

    @Autowired
    private CustomerService customerService;

    @Bean
    public Consumer<RequestCustomer> addCustomer() {
        return request -> {
            log.info("received: {}", request);
            customerService.requestCustomer(request);
        };
    }

    @Bean
    public Consumer<String> deleteCustomer() {
        return request -> {
            log.info("received: {}", request);
            List<Long> params = Arrays.stream(request.split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
            customerService.deleteCustomer(params.get(0));
        };
    }
}
