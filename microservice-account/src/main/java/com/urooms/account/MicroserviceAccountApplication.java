package com.urooms.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAccountApplication.class, args);
	}

}
