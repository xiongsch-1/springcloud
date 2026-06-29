package com.zhaowa.galaxy.springcloud.customer.feign.fallback;

import com.zhaowa.galaxy.springcloud.customer.feign.AService;
import com.zhaowa.galaxy.springcloud.servicea.api.Param;
import com.zhaowa.galaxy.springcloud.servicea.api.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AServiceFallbackFactory implements FallbackFactory<AService> {

    public static final Logger logger = LoggerFactory.getLogger(AServiceFallbackFactory.class);

    @Override
    public AService create(Throwable cause) {
        return new AService() {
            @Override
            public Result testPost(Param request) {
                logger.error("fallback testPost", cause);
                return new Result(1, "fallback testPost" + cause.toString());
            }

            @Override
            public Result testGet(String param) {
                logger.error("fallback testGet", cause);
                return new Result(2, "fallback testGet" + cause.toString());
            }
        };
    }
}
