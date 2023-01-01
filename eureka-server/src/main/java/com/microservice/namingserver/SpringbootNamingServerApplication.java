package com.microservice.namingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringbootNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNamingServerApplication.class, args);
	}

}
