package com.springservicediscovery.myrestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyrestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyrestappApplication.class, args);
	}

}
