package com.traveltimeaware.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.traveltimeaware.app.util.BingMapsRequest;
import com.traveltimeaware.app.util.CalendarInitializer;

import java.sql.*;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Configuration
@PropertySource("classpath:application-local.properties")
public class TravelTimeAwareApplication {

	@Value("${host}")
	private static String urlDatabase;
	
	@Value("${name}")
	private static String usernameDatabase;

	@Value("${password}")
	private static String passwordDatabase;

	public static void main(String[] args) {
		SpringApplication.run(TravelTimeAwareApplication.class, args);
		try {
			Connection connectionDatabase = DriverManager.getConnection(urlDatabase, usernameDatabase, passwordDatabase);
			System.out.println("Connessione riuscita");
			
		} catch (SQLException e) {
			System.out.println("Connessione non riuscita");
		}
		
		
		/*
		try {
			CalendarInitializer.start();
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		} */
	}

}