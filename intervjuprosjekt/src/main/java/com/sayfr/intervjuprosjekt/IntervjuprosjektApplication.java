package com.sayfr.intervjuprosjekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class IntervjuprosjektApplication {
	public static void main(String[] args) {
		SpringApplication.run(IntervjuprosjektApplication.class, args);
	}

}
