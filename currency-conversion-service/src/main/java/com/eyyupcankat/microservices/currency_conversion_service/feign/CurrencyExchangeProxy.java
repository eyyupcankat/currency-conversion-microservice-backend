package com.eyyupcankat.microservices.currency_conversion_service.feign;

import com.eyyupcankat.microservices.currency_conversion_service.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
@FeignClient(name = "currency-exchange-service")
/*
feign clienti alttaki gibi kullanmamızın sebebi
eureka clientin içinde gelen loadbalancerin
bizim açtığımız 8000 ya da 8001 e kendisinin yönlendirmesini
istiyoruz dolayısıyla belli bir url tanımlamamalıyız
 */
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);


}