package com.zhaowa.galaxy.springcloud.customer.controller;


import com.zhaowa.galaxy.springcloud.customer.service.CustomerService;
import com.zhaowa.galaxy.springcloud.servicea.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/test1")
    @ResponseBody
    public Mono<Result> test1(@RequestParam String name1, @RequestParam String name2) {
        return customerService.testPost(name1,name2);
    }

    @GetMapping("/test2")
    @ResponseBody
    public Mono<ResponseEntity<Result>> test2(@RequestParam String name1, @RequestParam String name2) {
        return customerService.testGet2(name1,name2);
    }

    @GetMapping("/test3")
    @ResponseBody
    public Mono<String> test3(@RequestParam String name1, @RequestParam String name2) {
        return customerService.testGet(name1,name2);
    }
}
