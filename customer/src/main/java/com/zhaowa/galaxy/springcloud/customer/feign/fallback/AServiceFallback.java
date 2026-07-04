package com.zhaowa.galaxy.springcloud.customer.feign.fallback;

import com.zhaowa.galaxy.springcloud.customer.feign.AService;
import com.zhaowa.galaxy.springcloud.servicea.api.Param;
import com.zhaowa.galaxy.springcloud.servicea.api.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AServiceFallback implements AService {
    public static Logger logger = LoggerFactory.getLogger(AServiceFallback.class);
    @Override
    public Result testPost(Param request) {
        logger.info("fallback testPost");
        return new Result(1, "post error");
    }

    @Override
    public Result testGet(String param) {
        logger.info("fallback testGet");
        return new Result(2, "get error");
    }

    @Override
    public Result testSentinel(String param) {
        logger.info("fallback testSentinel");
        return new Result(3, "sentinel error");
    }

    @Override
    public Result testSentinel2(String param) {
        logger.info("fallback testSentinel2");
        return new Result(4, "sentinel2 error");
    }
}
