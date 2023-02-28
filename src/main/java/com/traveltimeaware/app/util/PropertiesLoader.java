package com.traveltimeaware.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public abstract class PropertiesLoader {
	public static Properties loadProperties(String resourceFileName) throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = PropertiesLoader.class
          .getClassLoader()
          .getResourceAsStream(new ClassPathResource(resourceFileName).getPath());
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }
}
