package gateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class TokenRoutePredicateFactory extends
        AbstractRoutePredicateFactory<TokenRoutePredicateFactory.Config> {

    public TokenRoutePredicateFactory() {
        super(Config.class);
    }

    @Validated
    public static class Config {
        @NotEmpty
        private String headerName;
        @NotEmpty
        private String tokenPrefix;

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }

        public String getTokenPrefix() {
            return tokenPrefix;
        }

        public void setTokenPrefix(String tokenPrefix) {
            this.tokenPrefix = tokenPrefix;
        }
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("headerName", "tokenPrefix");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            // 获取请求头
            String token = exchange.getRequest()
                    .getHeaders()
                    .getFirst(config.headerName);

            // 判断token是否存在且包含指定前缀
            if (token != null && token.startsWith(config.tokenPrefix)) {
                String actualToken = token.substring(config.tokenPrefix.length());
                // 这里可以添加更复杂的token验证逻辑
                return !actualToken.isEmpty();
            }
            return false;
        };
    }
}
