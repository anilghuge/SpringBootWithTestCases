package com.example.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.testing.model")
public class SpringBootTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestingApplication.class, args);
	}

}
