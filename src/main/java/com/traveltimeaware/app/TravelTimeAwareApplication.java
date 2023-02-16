package com.traveltimeaware.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.traveltimeaware.app.util.BingMapsRequest;
import com.traveltimeaware.app.util.CalendarInitializer;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TravelTimeAwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelTimeAwareApplication.class, args);
		
		/*try {
			CalendarInitializer.start();
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		} */
		
		BingMapsRequest r = null;
		try {
			r = new BingMapsRequest.Builder("/Routes/Driving")
					.param("wp.0", "redmond,wa")
					.param("wp.1", "Issaquah,wa")
					.param("avoid", "minimizeTolls").build();
			
			System.out.println(r.getResponse());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
