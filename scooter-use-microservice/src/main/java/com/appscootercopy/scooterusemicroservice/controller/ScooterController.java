package com.appscootercopy.scooterusemicroservice.controller;

import com.appscootercopy.scooterusemicroservice.service.ScooterService;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.request.ScooterRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.response.ScooterResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooterStop.request.ScooterStopRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/scooters")
public class ScooterController {

    private ScooterService scooterService;

    public ScooterController(ScooterService scooterService) {
        this.scooterService = scooterService;
    }

    @GetMapping("/{licensePlate}")
    public ScooterResponseDTO getScooterByLicensePlate(@PathVariable Long licensePlate){
        return scooterService.findScooterByLicensePlate(licensePlate);
    }

    @GetMapping("/")
    public List<ScooterResponseDTO> getAllScooter(){
        return this.scooterService.findAllScooter();
    }

    @PostMapping("")
    public ResponseEntity saveScooter(@RequestBody @Valid ScooterRequestDTO request){
        return scooterService.saveScooter(request);
    }

    @DeleteMapping("/{id}")
    public void deleteScooter(@PathVariable Long id){
        this.scooterService.deleteScooter(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateScooter(@RequestBody @Valid ScooterRequestDTO scooter, @PathVariable Long idScooter){
        return this.scooterService.updateScooter(scooter, idScooter);
    }

    /*
    @PostMapping("/stops")
    public ResponseEntity createScooterStop(@RequestBody @Valid ScooterStopRequestDTO stopRequestDTO){
        return this.scooterService.saveScooterStop(stopRequestDTO);
    }*/
}
