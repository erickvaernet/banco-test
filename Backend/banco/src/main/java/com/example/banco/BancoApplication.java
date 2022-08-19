package com.example.banco;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoApplication {

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		SpringApplication.run(BancoApplication.class, args);
	}

}
