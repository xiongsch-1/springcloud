package com.zhaowa.galaxy.springcloud.servicea.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhaowa.galaxy.springcloud.servicea.api.Param;
import com.zhaowa.galaxy.springcloud.servicea.api.Result;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/servicea")
public class ServiceAController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAController.class);

    @PostMapping("/testPost")
    @ResponseBody
    public Result testPost(@RequestBody Param request) {
        logger.info("testPost: data={}", request);
        if (request.getParamA() == null || request.getParamA().isEmpty()) {
            throw new RuntimeException("param a cannot be empty!");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Result(200, "success: " + request.getParamA() + "," + request.getParamB());
    }

    @GetMapping("/testGet")
    @ResponseBody
    public Result testGet(@RequestParam String param) {
        logger.info("testGet: data={}", param);
        if (param.startsWith("err")) {
            throw new RuntimeException("testGet error!");
        }
        return new Result(200, "success: " + param);
    }

    @GetMapping("/testSentinel")
    @ResponseBody
    @SentinelResource(value = "testSentinel")
    public Result testSentinel(@RequestParam String param) {
        logger.info("testSentinel: data={}", param);
        return new Result(200, "testSentinel success: " + param);
    }

    @GetMapping("/testSentinel2")
    @ResponseBody
    @SentinelResource(
            value = "testSentinel2",
            blockHandler = "testSentinel2_block",
            fallback = "testSentinel2_fallback"
    )
    public Result testSentinel2(@RequestParam String param) {
        if (param.length() == 5) {
            throw new RuntimeException("test exception");
        }
        return new Result(200, "testSentinel2 success: " + param);
    }

    // 限流/熔断时的处理方法
    public Result testSentinel2_block(String param, BlockException exception) {
        logger.info("testSentinel2 被限流/熔断", exception);
        return new Result(1, "testSentinel2 block: " + param);
    }

    // 业务异常降级时的处理方法
    public Result testSentinel2_fallback(String param, Throwable throwable) {
        logger.warn("testSentienl2 降级！异常：{}", throwable.getMessage());
        return new Result(2, "testSentinel2 fallback: " + param);
    }

}
