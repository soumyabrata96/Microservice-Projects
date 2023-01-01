package com.microservice.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.currencyconversionservice.model.CurrencyConversion;
import com.microservice.currencyconversionservice.model.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	private Logger logger=LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateConvertedCurrency(@PathVariable String from,@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		HashMap<String,String> uriVariables=new HashMap<String,String>();
		uriVariables.put("from",from);
		uriVariables.put("to",to);
		ResponseEntity<CurrencyConversion> responseEntity=new RestTemplate().getForEntity(
				"http://currency-exchange-service:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,uriVariables);
		CurrencyConversion currencyConversion=responseEntity.getBody();
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,
				currencyConversion.getConversionMultiple(),currencyConversion.getConversionMultiple().multiply(quantity),
				currencyConversion.getEnvironment()+" "+"rest template");
		
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateConvertedCurrencyFeign(@PathVariable String from,@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		logger.info("Calculating Converted Currency from {} to {}",from,to);
		CurrencyConversion currencyConversion=currencyExchangeProxy.retrieveExchangeValue(from, to);
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,
				currencyConversion.getConversionMultiple(),currencyConversion.getConversionMultiple().multiply(quantity),
				currencyConversion.getEnvironment()+" "+"feign");
		
	}
}
