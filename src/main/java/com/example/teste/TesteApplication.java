package com.example.teste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TesteApplication {

//    public static final String BACKEND_VERSION = "1.0.0";
//    public static final String FRONTEND_VERSION = "0.0.2";

    public static void main(String[] args) {
        SpringApplication.run(TesteApplication.class, args);
    }
}
