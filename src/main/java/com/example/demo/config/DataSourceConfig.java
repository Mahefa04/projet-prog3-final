package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {
    private final String jdbcURl = System.getenv("JDBC_URl");
    private final String user = System.getenv("postgres");
    private final String password = System.getenv("1234");

    @Bean
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Agricultural_federation_db", "postgres", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
