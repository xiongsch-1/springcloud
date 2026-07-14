package gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

@Configuration
public class RedisLimitationConfig {

    // 限流的维度
    @Bean
    @Primary
    public KeyResolver remoteHostLimitationKey() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getRemoteAddress()
                        .getAddress()
                        .getHostAddress()
        );
    }

    // service-a服务限流规则
    @Bean("serviceaRateLimiter")
    public RedisRateLimiter serviceaRateLimiter() {
        return new RedisRateLimiter(10, 20);
    }

    // customer服务限流规则
    @Bean("customerRateLimiter")
    public RedisRateLimiter customerRateLimiter() {
        return new RedisRateLimiter(1, 2);
    }

    @Bean("defaultRateLimiter")
    @Primary
    public RedisRateLimiter defaultRateLimiter() {
        return new RedisRateLimiter(50, 100);
    }

}
