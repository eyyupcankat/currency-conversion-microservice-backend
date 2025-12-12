package com.eyyupcankat.microservices.limits_service.controller;

import com.eyyupcankat.microservices.limits_service.bean.Limits;
import com.eyyupcankat.microservices.limits_service.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping
    public Limits retrieveLimits(){
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}
