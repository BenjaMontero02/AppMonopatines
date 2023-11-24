package com.appscooter.tripmicroservice.controllers;

import com.appscooter.tripmicroservice.services.TripService;
import com.appscooter.tripmicroservice.services.dtos.generalprice.request.GeneralPriceRequestDTO;
import com.appscooter.tripmicroservice.services.dtos.generalprice.response.GeneralPriceResponseDTO;
import com.appscooter.tripmicroservice.services.dtos.tariff.request.TotalProfitsRequestDTO;
import com.appscooter.tripmicroservice.services.dtos.tariff.response.ReportProfitsDTO;
import com.appscooter.tripmicroservice.services.dtos.trip.requests.FinishTripRequestDTO;
import com.appscooter.tripmicroservice.services.dtos.trip.requests.TripRequestDTO;
import com.appscooter.tripmicroservice.services.dtos.trip.requests.TripsAndYearRequestDTO;
import com.appscooter.tripmicroservice.services.dtos.trip.responses.ReportScootersDTO;
import com.appscooter.tripmicroservice.services.dtos.trip.responses.ScooterByTripsYearResponseDTO;
import com.appscooter.tripmicroservice.services.dtos.trip.responses.TripResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/trips")
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

    @GetMapping("/scooter/{licensePlate}")
    public List<TripResponseDTO> getAllTripByScooter(@PathVariable String licensePlate) {
        return this.tripService.findAllTripByScooter(licensePlate);
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid TripRequestDTO request) {
        return tripService.saveTrip(request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity finishTrip(@RequestBody @Valid FinishTripRequestDTO request, @PathVariable Long id) {
        return this.tripService.finishTrip(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        this.tripService.deleteTrip(id);
    }

    @DeleteMapping("/license-scooter/{licenseScooter}")
    public void deleteTripByLicenseScooter(@PathVariable String licenseScooter) {
        this.tripService.deleteAllTripByLicenseScooter(licenseScooter);
    }

    @PutMapping("/{id}/stops")
    public void initPause(@PathVariable Long id) {
        this.tripService.initPause(id);
    }

    @DeleteMapping("/{id}/stops")
    public void cancelPause(@PathVariable Long id) {
        this.tripService.endPause(id);
    }

    @GetMapping("/profits")
    public List<ReportProfitsDTO> findProfitsByMonthsInYear(@Valid TotalProfitsRequestDTO request) {
        return this.tripService.findProfitsBetweenMonthsInYear(request);
    }

    @PostMapping("/prices")
    public ResponseEntity saveNewPrices(@RequestBody @Valid GeneralPriceRequestDTO request) {
        return tripService.saveNewPrices(request);
    }

    @GetMapping("/prices/")
    public List<GeneralPriceResponseDTO> findAllPrices() {
        return tripService.findHistoryPrices();
    }

    @GetMapping("/report/kms")
    public List<ReportScootersDTO> getReportUseScootersByKms() {
        return this.tripService.findUseScootersByKms();
    }

    @GetMapping("/report/pauses")
    public List<ReportScootersDTO> getReportUseScootersByTimeCcPauses() {
        return this.tripService.findUseScootersByTimeCcPauses();
    }

    @GetMapping("/report/non&pauses")
    public List<ReportScootersDTO> getReportUseScootersByTimeOutPauses() {
        return this.tripService.findUseScootersByTimeOutPauses();
    }

    @GetMapping("/scooters/trips&year")
    public List<ScooterByTripsYearResponseDTO> getAllScooterByTripsAndYear(@RequestBody @Valid TripsAndYearRequestDTO request){
        List<ScooterByTripsYearResponseDTO> list = this.tripService.findAllScooterByTripsAndYear(request);
        return list;
    }




}

