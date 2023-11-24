package com.appscooter.tripmicroservice;

import com.appscooter.tripmicroservice.domain.Trip;
import com.appscooter.tripmicroservice.services.dtos.trip.responses.TripResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TripTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetTrips() {
        ResponseEntity<List<TripResponseDTO>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/trips",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TripResponseDTO>>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<TripResponseDTO> trips = response.getBody();
        assertNotNull(trips);
        assertNotEquals(0, trips.size());
    }
}
