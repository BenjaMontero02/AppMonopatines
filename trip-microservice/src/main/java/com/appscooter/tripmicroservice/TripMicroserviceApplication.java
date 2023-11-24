package com.appscooter.tripmicroservice;

import com.appscooter.tripmicroservice.services.loadData.CsvReader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@EnableDiscoveryClient
public class TripMicroserviceApplication {

	@Autowired
	private CsvReader loadDb;

	public static void main(String[] args) {
		SpringApplication.run(TripMicroserviceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public WebClient.Builder webClient(){
		return WebClient.builder();
	}

	@PostConstruct
	public void init() throws SQLException, IOException {
		this.loadDb.load();
	}
}
