package com.ks.moviecatlogservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.ks.moviecatlogservice.controller")
public class MovieCatlogApp {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatlogApp.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate(){
		
		return new RestTemplate();
	}

}
