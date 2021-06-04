package com.setplex.ott.packages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SetplexPackagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SetplexPackagesApplication.class, args);
	}

}
