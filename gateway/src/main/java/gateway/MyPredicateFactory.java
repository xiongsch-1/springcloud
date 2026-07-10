package gateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

// 继承自通用扩展抽象类AbstractRoutePredicateFactory
@Component
public class MyPredicateFactory extends
        AbstractRoutePredicateFactory<MyPredicateFactory.Config> {
    public MyPredicateFactory() {
        super(Config.class);
    }
    // 定义当前谓词所需要用到的参数
    @Validated
    public static class Config {
        private String myField;

        public String getMyField() {
            return myField;
        }

        public void setMyField(String myField) {
            this.myField = myField;
        }
    }
    @Override
    public List<String> shortcutFieldOrder() {
        // 声明当前谓词参数的传入顺序
        // 参数名要和Config中的参数名称一致
        return Arrays.asList("myField");
    }
    // 实现谓词判断的核心方法
    // Gateway会将外部传入的参数封装为Config对象
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            // 在这个方法里编写自定义谓词逻辑
            @Override
            public boolean test(ServerWebExchange exchange) {
                return true;
            }
            @Override
            public String toString() {
                return String.format("myField: %s", config.myField);
            }
        };
    }
}
