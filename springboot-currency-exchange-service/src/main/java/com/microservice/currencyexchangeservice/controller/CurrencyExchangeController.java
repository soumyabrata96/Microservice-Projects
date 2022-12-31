package com.microservice.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.currencyexchangeservice.CurrencyExchangeRepository;
import com.microservice.currencyexchangeservice.model.CurrencyExchange;

@RestController
public class CurrencyExchangeController {

	private Logger logger=LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	private Environment env;
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {
		logger.info("Retrieving Currency Exchange value from {} to {}",from,to);
		CurrencyExchange currencyExchange = currencyExchangeRepo.findByFromAndTo(from, to);
		String port=env.getProperty("server.port");
		currencyExchange.setEnvironment("port : "+port);
		return currencyExchange;
	}
}
