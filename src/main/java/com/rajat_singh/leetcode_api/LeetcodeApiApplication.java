package com.rajat_singh.leetcode_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class LeetcodeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeetcodeApiApplication.class, args);
	}

}
