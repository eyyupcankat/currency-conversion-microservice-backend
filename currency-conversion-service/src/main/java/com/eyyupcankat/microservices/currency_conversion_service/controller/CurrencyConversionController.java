package com.eyyupcankat.microservices.currency_conversion_service.controller;

import com.eyyupcankat.microservices.currency_conversion_service.bean.CurrencyConversion;
import com.eyyupcankat.microservices.currency_conversion_service.feign.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

/*
    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){



        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/" + from + "/to/" + to, CurrencyConversion.class);

        CurrencyConversion currencyConversion = responseEntity.getBody();


        return new CurrencyConversion(currencyConversion.getId(),from,to,currencyConversion.getConversionMultiple(),quantity,quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment()+" normal");
        //return new CurrencyConversion(10001L,from,to, BigDecimal.ONE,quantity,BigDecimal.ONE,"");
    }
*/

    @GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(),from,to,currencyConversion.getConversionMultiple(),quantity,quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment()+" feign");
        //return new CurrencyConversion(10001L,from,to, BigDecimal.ONE,quantity,BigDecimal.ONE,"");
    }


}
