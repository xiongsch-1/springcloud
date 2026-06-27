package com.zhaowa.galaxy.springcloud.customer.service.impl;

import com.zhaowa.galaxy.springcloud.customer.service.CustomerService;
import com.zhaowa.galaxy.springcloud.servicea.api.Param;
import com.zhaowa.galaxy.springcloud.servicea.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.zhaowa.galaxy.springcloud.customer.constant.Constant.TRAFFIC_VERSION;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<Result> testPost(String param1, String param2) {
        Param param = new Param(param1, param2);
        return webClientBuilder.build().post()
                .uri("http://service-a/servicea/testPost")
                .bodyValue(param)
                .retrieve()
                .bodyToMono(Result.class);
    }

    @Override
    public Mono<String> testGet(String param1, String param2) {
        return webClientBuilder.build().get()
                .uri("http://service-a/servicea/testGet?param=" + param1 + "abc" + param2)
                .retrieve()
                .bodyToMono(Result.class)
                .map(Result::getMsg);
    }

    @Override
    public Mono<ResponseEntity<Result>> testGet2(String param1, String param2) {
        return webClientBuilder.build().get()
                .uri("http://service-a/servicea/testGet?param=" + param1 + "abc" + param2)
                .accept(MediaType.APPLICATION_JSON)
                .header(TRAFFIC_VERSION, param1.substring(0,3))
                .retrieve()
                .toEntity(Result.class);
    }
}
