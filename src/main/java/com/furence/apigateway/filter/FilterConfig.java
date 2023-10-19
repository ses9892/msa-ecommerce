package com.furence.apigateway.filter;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator getewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route( r -> r.path("/user-service1/**")
                        .filters( f -> f.addRequestHeader("user-service1" , "user-service1-header")
                            .addResponseHeader("user-service1-response" , "user-service1-res-header")
                            .addRequestParameter("serialId" , UUID.randomUUID().toString()))
                        .uri("http://localhost:9001")
                )
                .route( r -> r.path("/user-service2/**")
                        .filters( f -> f.addRequestHeader("user-service2" , "user-service2-header")
                            .addResponseHeader("user-service2-response" , "user-service2-res-header")
                            .addRequestParameter("serialId" , UUID.randomUUID().toString()))
                        .uri("http://localhost:9002")
                )
                .build();
    }
}
