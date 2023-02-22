package com.traveltimeaware.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


// user@email.com/pass
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
@Configuration
public class TravelTimeAwareApplication {
	
	private String urlDatabase;

	private String usernameDatabase;

	private String passwordDatabase;

	private String portDatabase;

	public static void main(String[] args) {
		SpringApplication.run(TravelTimeAwareApplication.class, args);
	}

}