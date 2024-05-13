package com.example.MoviesData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MoviesDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesDataApplication.class, args);
	}

}
