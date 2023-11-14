package com.appscootercopy.scooterusemicroservice.service;
import com.appscootercopy.scooterusemicroservice.domain.Trip;
import com.appscootercopy.scooterusemicroservice.repository.TripRepository;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.request.TripRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.response.TripResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.exception.ConflictExistException;
import com.appscootercopy.scooterusemicroservice.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("TripService")
public class TripService {

    private TripRepository tripRepository;

    public TripService(TripRepository t) {
        this.tripRepository=t;
    }
    @Transactional(readOnly = true)
    public TripResponseDTO findTripById(long id) {
        return tripRepository.findById(id).map(TripResponseDTO::new).orElseThrow(() -> new NotFoundException("Client", "ID", id));
    }

    @Transactional(readOnly = true)
    public List<TripResponseDTO> findAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(s1-> new TripResponseDTO(s1)).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity saveTrip(TripRequestDTO request) {
        if(!this.tripRepository.existsById(request.getId())){
            this.tripRepository.save(new Trip(request));
            return new ResponseEntity(request.getId(), HttpStatus.CREATED);
        }

        throw new ConflictExistException("Trip", "ID", request.getId());
    }
    @Transactional
    public ResponseEntity updateTrip(TripRequestDTO trip, Long id) {
        Trip tr = this.tripRepository.getById(id);
        if(tr != null){
            tr.setKms(trip.getKms());
            tr.setInitTime(trip.getInitTime());
            tr.setEndTime(trip.getEndTime());
            tr.setEnded(trip.getEnded());
        }
        throw new NotFoundException("Trip", "Id", id);
    }
    @Transactional
    public void deleteTrip(Long id) {
        if(this.tripRepository.existsById(id)){
            this.tripRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Trip", "Id", id);
        }
    }
}
