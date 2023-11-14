package com.ScootersApp;


import com.ScootersApp.Service.loadData.CSVReader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class UserMicroserviceApplication {
    @Autowired
    private CSVReader loadDb;
    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }
    @PostConstruct
    public void init() throws SQLException, IOException {
        this.loadDb.load();
    }
}