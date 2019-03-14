package com.yyh.authoritymanagement.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yyh.authoritymanagement.dao")
public class MybatisConfig {
}
