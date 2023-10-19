package com.furence.netfilxzuul.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furence.netfilxzuul.util.HttpUtil;
import com.furence.netfilxzuul.util.testDto;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@Slf4j
@Configuration
public class ZuulLogSufFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "post" ;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("post");
        String responseBody = HttpUtil.getResponseBody(RequestContext.getCurrentContext());
        log.info("res : {}" ,responseBody.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            testDto testDto = objectMapper.readValue(responseBody, testDto.class);
            log.info("objectMapper : {}" ,testDto);
        } catch (JsonProcessingException e) {
            log.info("e : {}" , e.getMessage());
        }
        return null;
    }
}
