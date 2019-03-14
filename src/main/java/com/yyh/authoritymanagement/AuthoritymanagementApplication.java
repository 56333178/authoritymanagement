package com.yyh.authoritymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class AuthoritymanagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthoritymanagementApplication.class, args);
	}
}

