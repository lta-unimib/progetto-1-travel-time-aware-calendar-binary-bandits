package com.traveltimeaware.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.traveltimeaware.app.util.CalendarInitializer;

import java.sql.*;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TravelTimeAwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelTimeAwareApplication.class, args);

		//connessione al database
		/*String urlDatabase = "url del database";
		String usernameDatabase = "username";
		String passwordDatabase = "password";
		
		try {
			Connection connectionDatabase = DriverManager.getConnection(urlDatabase, usernameDatabase, passwordDatabase);
			System.out.println("Connessione riuscita");
			
		} catch (SQLException e) {
			System.out.println("Connessione non riuscita");
		}*/
		
		try {
			CalendarInitializer.start();
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		} 
	}

}