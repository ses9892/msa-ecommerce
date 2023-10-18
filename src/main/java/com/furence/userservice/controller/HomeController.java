package com.furence.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping(value = "/welcome")
    public String welcome(){
        return appName + " service welcome";
    }

}
