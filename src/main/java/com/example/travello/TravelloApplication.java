package com.example.travello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class TravelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelloApplication.class, args);
	}
}
