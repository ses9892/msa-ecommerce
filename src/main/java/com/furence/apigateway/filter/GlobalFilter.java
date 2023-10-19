package com.furence.apigateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config>  {


    public GlobalFilter() {
        super(GlobalFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return  new OrderedGatewayFilter((exchange, chain) -> {
            log.info("test");
            log.info(config.getBaseMeg());

            // pre
            if(config.isPreUse()){
                log.info("Global Filter Pre");
            }

            // post
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(config.isPostUse()){
                    log.info("Global Filter Post");
                }
            }));
        } , Ordered.LOWEST_PRECEDENCE);

    }

    @Data
    public static class Config{

        private String baseMeg;
        private boolean preUse;
        private boolean postUse;

    }

}
