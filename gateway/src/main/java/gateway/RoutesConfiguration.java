package gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class RoutesConfiguration {

    @Autowired
    private CustomTokenRoutePredicateFactory tokenPredicateFactory;

    @Autowired
    private KeyResolver hostAddrKeyResolver;

    @Autowired
    @Qualifier("customerRateLimiter")
    private RateLimiter customerRateLimiter;

    @Autowired
    @Qualifier("serviceaRateLimiter")
    private RateLimiter serviceaRateLimiter;

    @Bean
    public RouteLocator declare(RouteLocatorBuilder builder) {
        return builder.routes()
//                .route(route -> route
//                        .path("/gateway/customer/**")
//                        .filters(f -> f.stripPrefix(1))
//                        .uri("lb://customer")
                .route(route -> route
                        .order(1)
                        .path("/gateway/servicea/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://service-a")
//                ).route("id-001", route -> route
//                        .path("/baidu/**")
//                        .and()
//                        .method(HttpMethod.GET, HttpMethod.POST)
//                        .uri("www.baidu.com")
//                ).route("id-002", route -> route
//                        // 验证cookie
//                        .cookie("myCookie", "regex")
//                        // 验证header
//                        .and().header("myHeaderA")
//                        .and().header("myHeaderB", "regex")
//                        // 验证param
//                        .and().query("paramA")
//                        .and().query("paramB", "regex")
//                        .and().remoteAddr("192.168.1.0/24")  // 只允许内网IP访问
//                        .and().host("**.example.com")        // 且Host头必须是example.com域名
//                        .uri("www.qq.com")
//                ).route("id-001", route -> route
//                        // 在指定时间之前
//                        .before(ZonedDateTime.parse("2025-12-25T14:33:47.789+08:00"))
//                        // 在指定时间之后
//                        .or().after(ZonedDateTime.parse("2025-12-25T14:33:47.789+08:00"))
//                        // 或者在某个时间段以内
//                        .or().between(
//                                ZonedDateTime.parse("起始时间"),
//                                ZonedDateTime.parse("结束时间"))
//                        .uri("seckill.zhaowa.com")
//                ).route("custom_route", r -> r
//                        .path("/customer/**")
//                        // 使用自定义谓词
//                        .and()
//                        .predicate(tokenPredicateFactory.apply(config -> {
//                            config.setHeaderName("Authorization");
//                            config.setTokenPrefix("Bearer");
//                        }))
//                        .uri("http://customer")
                ).route(route -> route
                        .order(2)
                        .path("/gateway/service-a/**")
                        .filters(f -> f.stripPrefix(1)
                                // 修改Request参数
                                .removeRequestHeader("zhaowa")
                                .addRequestHeader("ZhaoWA", "yes")
                                .removeRequestParameter("galaxy")
                                .addRequestParameter("Galaxy", "me")
                                // response系列参数 不一一列举了
                                .removeResponseHeader("responseHeader")
                        )
                        .uri("lb://service-a"))
                .route(route -> route.path("/gateway/customer/**")
                        .filters(f -> f.stripPrefix(1)
                                .requestRateLimiter(limiter-> {
                                            limiter.setKeyResolver(hostAddrKeyResolver);
                                            limiter.setRateLimiter(customerRateLimiter);
                                            // 限流失败后返回的HTTP status code
                                            limiter.setStatusCode(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
                                        }
                                )
                        )
                        .uri("lb://customer"))
                .build();
    }

}
