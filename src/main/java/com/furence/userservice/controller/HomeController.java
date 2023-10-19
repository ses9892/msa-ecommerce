package com.furence.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user-service1")
@Slf4j
public class HomeController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping(value = "/welcome")
    public String welcome(@RequestHeader("user-service1")String header, HttpServletRequest request){
        String serialId = request.getParameter("serialId");

        log.info("header value : {}" , header );
        log.info("serialId value : {}" , serialId );

        return appName + " service welcome";
    }

}
