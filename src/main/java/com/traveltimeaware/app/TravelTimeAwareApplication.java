package com.traveltimeaware.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.traveltimeaware.app.util.CalendarInitializer;


// user@email.com/pass
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
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