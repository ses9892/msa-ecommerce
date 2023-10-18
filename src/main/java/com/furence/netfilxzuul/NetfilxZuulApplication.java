package com.furence.netfilxzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class NetfilxZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetfilxZuulApplication.class, args);
    }

}
