package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {
    private final String JDBC_URl = System.getenv("JDBC_URL"); //
    private final String USER = System.getenv("USER"); //mini_dish_db_manager
    private final String PASSWORD = System.getenv("PASSWORD"); //123456

    @Bean
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/agriculture_federation_db", "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
