package gateway.dynamic;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;

@Component
public class DynamicRoutesListener implements Listener {

    private static final Logger log = LoggerFactory.getLogger(DynamicRoutesListener.class);

    @Autowired
    private GatewayService gatewayService;

    @Override
    public Executor getExecutor() {
        log.info("getExecutor");
        return null;
    }

    // 使用JSON转换，将plain text变为RouteDefinition
    @Override
    public void receiveConfigInfo(String configInfo) {
        log.info("received routes changes {}", configInfo);

        List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
        gatewayService.updateRoutes(definitionList);
    }
}
