package com.learning.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 to enable dynamic configuration updates for beans in a Spring application without requiring a restart.
  It essentially allows for the reloading of bean properties at runtime, triggered by a refresh event, often initiated through the /actuator/refresh endpoint.
*/
@RefreshScope
@RestController
@RequestMapping("/api")
public class Message {

    @Value("${spring.boot.message}")
    private String message;

    // GET http://localhost:8080/api/message
    /* API to refresh the changes from external config file
     POST http://localhost:8080/actuator/refresh
     request body: none*/
    @GetMapping("/message")
    public String getMessage(){
        return message;
    }
}
