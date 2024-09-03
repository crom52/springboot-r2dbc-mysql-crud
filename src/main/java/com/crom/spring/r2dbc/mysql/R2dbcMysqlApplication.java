package com.crom.spring.r2dbc.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
@Import(DataSourceAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.crom.spring.r2dbc.mysql.repository.jpa")
@EnableR2dbcRepositories(basePackages = "com.crom.spring.r2dbc.mysql.repository.r2dbc")
public class R2dbcMysqlApplication {

   public static void main(String[] args) {

      SpringApplication.run(R2dbcMysqlApplication.class, args);
   }

}
