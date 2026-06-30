package com.zhaowa.galaxy.springcloud.servicea.controller;


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
        return new Result(200, "success: " + param);
    }
}
