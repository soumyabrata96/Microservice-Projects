package com.microservice.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.currencyexchangeservice.model.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

	public CurrencyExchange findByFromAndTo(String from,String to);
}
