package com.example.Cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheInbuiltSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheInbuiltSbApplication.class, args);
	}

}
