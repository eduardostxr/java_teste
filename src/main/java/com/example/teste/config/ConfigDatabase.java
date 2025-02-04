package com.example.teste.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ConfigDatabase {
   @Primary
   @Bean(name = {"teste-properties"})
   @ConfigurationProperties(prefix = "spring.datasource.config")
   public HikariConfig avantiAgroProperties() {
      return (HikariConfig)DataSourceBuilder.create().type(HikariDataSource.class).build();
   }

   @Bean(name = {"teste-hikari"})
   public HikariDataSource avantiAgroHikari(@Qualifier("teste-properties") HikariConfig hikariConfig) {
      return new HikariDataSource(hikariConfig);
   }

   @Bean(name = {"teste-pool"})
   public JdbcTemplate getPool(@Qualifier("teste-hikari") HikariDataSource dataSource) {
      return new JdbcTemplate(dataSource);
   }
}
