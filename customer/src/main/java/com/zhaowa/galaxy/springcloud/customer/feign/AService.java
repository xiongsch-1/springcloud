package com.zhaowa.galaxy.springcloud.customer.feign;

import com.zhaowa.galaxy.springcloud.customer.feign.fallback.AServiceFallback;
import com.zhaowa.galaxy.springcloud.customer.feign.fallback.AServiceFallbackFactory;
import com.zhaowa.galaxy.springcloud.servicea.api.Param;
import com.zhaowa.galaxy.springcloud.servicea.api.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-a", path = "/servicea",
//    fallback = AServiceFallback.class
    fallbackFactory = AServiceFallbackFactory.class
)
public interface AService {
    @PostMapping("/testPost")
    Result testPost(@RequestBody Param request);

    @GetMapping("/testGet")
    Result testGet(@RequestParam String param);

    @GetMapping("/testSentinel")
    Result testSentinel(@RequestParam String param);

    @GetMapping("/testSentinel2")
    Result testSentinel2(@RequestParam String param);
}
