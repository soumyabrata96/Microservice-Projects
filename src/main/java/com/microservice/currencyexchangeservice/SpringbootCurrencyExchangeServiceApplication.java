package com.microservice.currencyexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootCurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCurrencyExchangeServiceApplication.class, args);
	}

}
