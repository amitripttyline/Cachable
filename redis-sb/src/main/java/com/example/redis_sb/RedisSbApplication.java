package com.example.redis_sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisSbApplication.class, args);
	}

}
