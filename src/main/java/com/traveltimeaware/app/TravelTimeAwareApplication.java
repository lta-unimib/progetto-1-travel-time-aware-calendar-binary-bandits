package com.traveltimeaware.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.traveltimeaware.app.util.CalendarInitializer;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TravelTimeAwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelTimeAwareApplication.class, args);
		
		try {
			CalendarInitializer.start();
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
	}

}
