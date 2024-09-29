package com.crom.spring.r2dbc.mysql.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class R2dbcTransactionConfig {

   @Primary
   @Bean
   public ReactiveTransactionManager reactiveTransactionManager(ConnectionFactory connectionFactory) {
      return new R2dbcTransactionManager(connectionFactory);
   }
}
