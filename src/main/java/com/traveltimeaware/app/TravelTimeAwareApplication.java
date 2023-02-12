package com.traveltimeaware.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TravelTimeAwareApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TravelTimeAwareApplication.class, args);
	}

}
