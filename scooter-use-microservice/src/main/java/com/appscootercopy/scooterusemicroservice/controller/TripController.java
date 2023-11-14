package com.appscootercopy.scooterusemicroservice.controller;

import com.appscootercopy.scooterusemicroservice.service.TripService;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.request.TripRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.response.TripResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid TripRequestDTO request) {
        return tripService.saveTrip(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTrip(@RequestBody @Valid TripRequestDTO trip, @PathVariable Long id) {
        return this.tripService.updateTrip(trip, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id){
        this.tripService.deleteTrip(id);
    }
}
