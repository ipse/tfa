package com.thefinancialapplication.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.thefinancialapplication.persistence"})
@EntityScan("com.thefinancialapplication.domain.model")
@ComponentScan(basePackages = {"com.thefinancialapplication"})
public class FinancialApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialApplication.class, args);
	}

}
