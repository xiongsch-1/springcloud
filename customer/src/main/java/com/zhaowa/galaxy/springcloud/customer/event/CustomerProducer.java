package com.zhaowa.galaxy.springcloud.customer.event;

import com.zhaowa.galaxy.springcloud.customer.beans.RequestCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class CustomerProducer {

    private static final Logger log = LoggerFactory.getLogger(CustomerProducer.class);

    @Autowired
    private StreamBridge streamBridge;

    public void sendCustomer(RequestCustomer requestCustomer) {
        log.info("sent: {}", requestCustomer);
        streamBridge.send(EventConstant.ADD_CUSTOMER_EVENT, requestCustomer);
    }

    public void deleteCustomer(Long userId) {
        log.info("sent delete customer event: userId={}", userId);
        streamBridge.send(EventConstant.DELETE_CUSTOMER_EVENT, userId);
    }

}
