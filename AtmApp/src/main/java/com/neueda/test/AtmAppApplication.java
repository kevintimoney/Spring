package com.neueda.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class AtmAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmAppApplication.class, args);
	}
	

}
