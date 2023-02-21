package com.traveltimeaware.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Configuration
public class TravelTimeAwareApplication {

	@Value("${host}")
	private static String urlDatabase;

	@Value("${name}")
	private static String usernameDatabase;

	@Value("${password}")
	private static String passwordDatabase;

	@Value("{port}")
	private static String portDatabase;

	public static void main(String[] args) {
		SpringApplication.run(TravelTimeAwareApplication.class, args);
		
		System.out.print(portDatabase);
	}

}