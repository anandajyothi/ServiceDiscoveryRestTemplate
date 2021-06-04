package com.setplex.ott.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SetplexCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SetplexCloudGatewayApplication.class, args);
	}

}
