package com.eyyupcankat.microservices.currency_exchange_service.controller;

import com.eyyupcankat.microservices.currency_exchange_service.bean.CurrencyExchange;
import com.eyyupcankat.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;


    @Autowired
    private Environment environment;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange == null){
            throw new RuntimeException("Currency Exchange not found for " + from + " to " + to);
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

        //return new CurrencyExchange(1000L,from,to, BigDecimal.valueOf(50),environment.getProperty("local.server.port"));
        return currencyExchange;
    }

}
