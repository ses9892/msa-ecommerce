package com.furence.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config>  {

    public CustomFilter() {
        super(Config.class);
    }

    // CustomFilter apply
    @Override
    public GatewayFilter apply(Config config) {

        // Custom Pre Filter
        return new OrderedGatewayFilter( (exchange, chain) -> {
            log.info(" Custom Pre Filter");

            ServerHttpRequest request = exchange.getRequest();

            request.getHeaders().keySet().forEach( key -> {
                log.info("header : {}" , key);
            });
            return chain.filter(exchange).then(Mono.fromRunnable( () -> {
                log.info("Custom Post Filter");

                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().keySet().forEach( key -> {
                    log.info("header : {}" , key);
                });

            }));
        },Ordered.HIGHEST_PRECEDENCE );
    }


    public static class Config{

    }


}
