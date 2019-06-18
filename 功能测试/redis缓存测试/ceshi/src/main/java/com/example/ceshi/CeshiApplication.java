package com.example.ceshi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CeshiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeshiApplication.class, args);
	}

}
