package com.zhaowa.galaxy.springcloud.customer.controller;


import com.zhaowa.galaxy.springcloud.customer.beans.RequestCustomer;
import com.zhaowa.galaxy.springcloud.customer.event.CustomerProducer;
import com.zhaowa.galaxy.springcloud.customer.service.CustomerService;
import com.zhaowa.galaxy.springcloud.servicea.api.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
@RefreshScope
public class CustomerController {
    public static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerProducer customerProducer;

    @Value("${disableRequest:false}")
    private Boolean disable;

    @GetMapping("/test1")
    @ResponseBody
    public Result test1(@RequestParam String name1, @RequestParam String name2) {
        return customerService.testPost(name1,name2);
    }

    @GetMapping("/test2")
    @ResponseBody
    public Mono<ResponseEntity<Result>> test2(@RequestParam String name1, @RequestParam String name2) {
        return customerService.testGet2(name1,name2);
    }

    @GetMapping("/test3")
    @ResponseBody
    public String test3(@RequestParam String name1, @RequestParam String name2) {
        log.info("name1={} name2={}", name1, name2);
        return customerService.testGet(name1,name2);
    }

    @GetMapping("/test4")
    @ResponseBody
    public Result test4(@RequestParam String name1, @RequestParam String name2) {
        if (disable) {
            return new Result(0, "remote service disabled!");
        }
        return customerService.testPost(name1,name2);
    }

    @GetMapping("/testSentinel")
    @ResponseBody
    public Result testSentinel(@RequestParam String name1, @RequestParam String name2) {
        return customerService.testSentinel(name1,name2);
    }

    @GetMapping("/testSentinel2")
    @ResponseBody
    public Result testSentinel2(@RequestParam String name1, @RequestParam String name2) {
        return customerService.testSentinel2(name1,name2);
    }

    @GetMapping("/testSentinel3")
    @ResponseBody
    public Result testSentinel3(@RequestParam String name1, @RequestParam String name2) {
        return customerService.testSentinel(name1,name2);
    }

    @GetMapping("/warmUp")
    @ResponseBody
    public Result warmUp(@RequestParam String name1, @RequestParam String name2) {
        return new Result(0, "warmup:" + name1 + "+" + name2);
    }

    @GetMapping("/testZipkin")
    @ResponseBody
    public String testZipkin(@RequestParam String name1, @RequestParam String name2) {
        log.info("name1={} name2={}", name1, name2);
        return customerService.testGet(name1, name2);
    }

    @GetMapping("/sendCustomerEvent")
    public void sendCustomerEvent(@RequestParam Long userId, @RequestParam String userName) {
        RequestCustomer requestCustomer = new RequestCustomer(userId, userName);
        customerProducer.sendCustomer(requestCustomer);
    }

    @GetMapping("/deleteCustomerEvent")
    public void deleteCustomerEvent(@RequestParam Long userId) {
        customerProducer.deleteCustomer(userId);
    }


}
