package com.zhaowa.galaxy.springcloud.customer.service;


import com.zhaowa.galaxy.springcloud.servicea.api.Result;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Result testPost(String param1, String param2);
    String testGet(String param1, String param2);
    Mono<ResponseEntity<Result>> testGet2(String param1, String param2);
}
