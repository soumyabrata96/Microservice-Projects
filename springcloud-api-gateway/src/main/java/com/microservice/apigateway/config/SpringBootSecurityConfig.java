package com.microservice.apigateway.config;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SpringBootSecurityConfig {

	@Value("security.user")
	private String username;
	@Value("security.password")
	private String password;
	@Bean
    public MapReactiveUserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("username")
				.password(bCryptPasswordEncoder()
				.encode("password")).roles("ACTUATOR").build();
//            .username("user")
//            .password("user")
//            .roles("ACTUATOR")
//            .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> {
            exchanges.matchers(EndpointRequest.toAnyEndpoint())
                                              .hasRole("ACTUATOR");
            exchanges.anyExchange().permitAll();
        }).httpBasic(Customizer.withDefaults());
        return http.build();

    }
    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
 
}
