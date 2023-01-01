package com.microservice.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api-retry")
	@Retry(name = "sample-api",fallbackMethod="sampleFallback")
	public String getSampleAPI() {
		logger.info("Sample API Retry call is received");
		return new RestTemplate().getForObject("http://localhost:8080/sample-dummy-api", String.class);
	}
	
	@GetMapping("/sample-api-circuitbreaker")
	@CircuitBreaker(name = "sample-api-1",fallbackMethod="sampleFallback")
	public String getSampleAPI1() {
		logger.info("Sample API Circuit Breaker call is received");
		return new RestTemplate().getForObject("http://localhost:8080/sample-dummy-api", String.class);
	}
	public String sampleFallback(Exception ex) {
		logger.info("Fallback method call is received");
		return "Fall Backkkkk !!!";
	}
	
	@GetMapping("/sample-api-ratelimiter")
	@RateLimiter(name = "sample-api-2")
	//@Bulkhead(name = "sample-api-2")
	public String getSampleAPI2() {
		logger.info("Sample API Rate Limiter call is received");
		return "Sample API";
	}
}
