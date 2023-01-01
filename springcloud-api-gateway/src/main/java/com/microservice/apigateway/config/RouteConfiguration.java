package com.microservice.apigateway.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RouteConfiguration {

	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p->p.path("/get")
						.filters(f->f.addRequestHeader("MyHeader", "MyHeader")
								.addRequestParameter("RequestParams", "MyRequestParams")).
						uri("http://httpbin.org:80"))
				.route(p->p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				.route(p->p.path("/currency-conversion/**").uri("lb://currency-conversion"))
				.route(p->p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
				.build();		
	}
}
