package com.bezkoder.spring.r2dbc.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableR2dbcRepositories
@SpringBootApplication
public class R2dbcMysqlApplication {

   public static void main(String[] args) {

      SpringApplication.run(R2dbcMysqlApplication.class, args);
   }

}
