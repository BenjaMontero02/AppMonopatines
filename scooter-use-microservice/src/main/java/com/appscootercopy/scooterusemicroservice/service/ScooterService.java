package com.appscootercopy.scooterusemicroservice.service;
import com.appscootercopy.scooterusemicroservice.domain.Scooter;
import com.appscootercopy.scooterusemicroservice.domain.ScooterStop;
import com.appscootercopy.scooterusemicroservice.repository.ScooterRepository;
import com.appscootercopy.scooterusemicroservice.repository.ScooterStopRepository;
import com.appscootercopy.scooterusemicroservice.repository.ScooterTripRepository;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.request.ScooterRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.response.ScooterResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooterStop.request.ScooterStopRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.exception.ConflictExistException;
import com.appscootercopy.scooterusemicroservice.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service("ScooterService")
public class ScooterService {

    private ScooterRepository scooterRepository;
    private ScooterStopRepository scooterStopRepository;
    private ScooterTripRepository scooterTripRepository;

    public ScooterService(ScooterRepository s, ScooterStopRepository ss, ScooterTripRepository st) {
        this.scooterRepository=s;
        this.scooterStopRepository=ss;
        this.scooterTripRepository=st;
    }

    @Transactional(readOnly = true)
    public ScooterResponseDTO findScooterByLicensePlate(Long licensePlate) {
        Scooter scooterFound = scooterRepository.findByLicensePLate(licensePlate);
        if(scooterFound!=null) {
            return new ScooterResponseDTO(scooterFound);
        }

        throw new NotFoundException("Scooter", "licencePlate (Long)", licensePlate);
    }

    @Transactional(readOnly = true)
    public List<ScooterResponseDTO> findAllScooter() {
        List<Scooter> scooters = scooterRepository.findAll();
        return scooters.stream().map(s1-> new ScooterResponseDTO(s1)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ScooterResponseDTO> findAllScooterWithUbication() {
        List<Scooter> scooters = scooterRepository.findAllfetchingUbication();

        return scooters
                .stream()
                .map(s1-> new ScooterResponseDTO(s1)).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity saveScooter(ScooterRequestDTO request) {
        if(!this.scooterRepository.existsByLicensePLate(request.getLicensePlate())) {
            this.scooterRepository.save(new Scooter(request));
            return new ResponseEntity(request.getLicensePlate(), HttpStatus.CREATED);
        }

        throw new ConflictExistException("Scooter", "licensePlate (Long)", request.getLicensePlate());
    }

    @Transactional
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteScooter(Long id){
        if(this.scooterRepository.existsById(id)) {
            this.scooterRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Scooter", "Id", id);
        }
    }

    @Transactional
    public ResponseEntity updateScooter(ScooterRequestDTO request, Long idScooter) {
        Scooter scooterExisting = this.scooterRepository.getById(idScooter);
        if(scooterExisting != null){
            scooterExisting.setLicensePLate(request.getLicensePlate());
            scooterExisting.setAvailable(request.getAvailable());
            scooterExisting.setUbication(request.getUbication());
            return new ResponseEntity(idScooter, HttpStatus.ACCEPTED);
        }
        throw new NotFoundException("Scooter", "Long", idScooter);
    }

    /*
    public ResponseEntity saveScooterStop(ScooterStopRequestDTO stop) {
        ScooterStop sameScooterStop = this.scooterStopRepository.findByUbication();
        if(){
            this.scooterStopRepository.save(new ScooterStop(stop));
            return new ResponseEntity(stop, HttpStatus.CREATED);
        }
        throw new ConflictExistException("ScooterStop", "Long", );
    }*/
}
