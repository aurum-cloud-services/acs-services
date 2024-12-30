package com.aurum.acs_services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcsServicesApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

		String port = dotenv.get("PORT", "14567");

		System.setProperty("server.port", port);

		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(AcsServicesApplication.class, args);
	}

}
