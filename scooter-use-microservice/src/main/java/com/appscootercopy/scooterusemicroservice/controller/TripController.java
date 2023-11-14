package com.appscootercopy.scooterusemicroservice.controller;

import com.appscootercopy.scooterusemicroservice.domain.ScooterStop;
import com.appscootercopy.scooterusemicroservice.repository.TariffRepository;
import com.appscootercopy.scooterusemicroservice.service.TripService;
import com.appscootercopy.scooterusemicroservice.service.dto.prices.request.GeneralPriceRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.prices.response.GeneralPriceResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooterTrip.response.ScooterTripResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.tariff.ReportProfitsDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.tariff.TariffRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.request.FinishTripRequestDTO;
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

    @GetMapping("/{id}")
    public TripResponseDTO findTripById(@PathVariable Long id) {
        return tripService.findTripById(id);
    }

    @GetMapping("/")
    public List<TripResponseDTO> findAllTrip() {
        return tripService.findAllTrip();
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid TripRequestDTO request) {
        return tripService.saveTrip(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTrip(@RequestBody @Valid TripRequestDTO request, @PathVariable Long id) {
        return this.tripService.updateTrip(request, id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity finishTrip(@RequestBody @Valid FinishTripRequestDTO request, @PathVariable Long id) {
        return this.tripService.finishTrip(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id){
        this.tripService.deleteTrip(id);
    }

    @GetMapping("/{id}/scooter")
    public ScooterTripResponseDTO getScooterTripByTripId(@PathVariable Long id) {
        return this.tripService.findScooterTripByTripId(id);
    }

    @PutMapping("/{id}/stops")
    public void initPause(@PathVariable Long id) {
        this.tripService.initPause(id);
    }

    @DeleteMapping("/{id}/stops")
    public void cancelPause(@PathVariable Long id) {
        this.tripService.endPause(id);
    }

    @GetMapping("/profits/{year}")
    public List<ReportProfitsDTO> findProfitsByMonthsInYear(@PathVariable Long year) {
        return this.tripService.findProfitsByMonthsInYear(year);
    }

    @PostMapping("/prices")
    public ResponseEntity saveNewPrices(@RequestBody @Valid GeneralPriceRequestDTO request) {
        return tripService.saveNewPrices(request);
    }

    @GetMapping("/prices/")
    public List<GeneralPriceResponseDTO> findAllPrices() {
        return tripService.findHistoryPrices();
    }


}
