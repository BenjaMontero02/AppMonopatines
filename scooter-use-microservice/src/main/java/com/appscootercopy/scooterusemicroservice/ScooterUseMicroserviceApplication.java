package com.appscootercopy.scooterusemicroservice;

import com.appscootercopy.scooterusemicroservice.service.loadData.CsvReader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@EnableDiscoveryClient
public class ScooterUseMicroserviceApplication {

	@Autowired
	private CsvReader loadDb;

	public static void main(String[] args) {
		SpringApplication.run(ScooterUseMicroserviceApplication.class, args);
	}

	@PostConstruct
	public void init() throws SQLException, IOException {
		this.loadDb.load();
	}

}
