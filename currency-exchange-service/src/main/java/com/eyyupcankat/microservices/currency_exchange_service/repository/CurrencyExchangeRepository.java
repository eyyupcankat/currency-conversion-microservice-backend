package com.eyyupcankat.microservices.currency_exchange_service.repository;

import com.eyyupcankat.microservices.currency_exchange_service.bean.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
