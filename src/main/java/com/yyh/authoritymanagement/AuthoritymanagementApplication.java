package com.yyh.authoritymanagement;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yyh.authoritymanagement.dao")
public class AuthoritymanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthoritymanagementApplication.class, args);
	}
}

