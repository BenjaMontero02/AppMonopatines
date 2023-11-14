package com.appscootercopy.scooterusemicroservice.service;
import com.appscootercopy.scooterusemicroservice.domain.*;
import com.appscootercopy.scooterusemicroservice.repository.*;
import com.appscootercopy.scooterusemicroservice.service.dto.prices.request.GeneralPriceRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.prices.response.GeneralPriceResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.tariff.ReportProfitsDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.tariff.TariffRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.request.FinishTripRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.exception.InvalidScooterStopException;
import com.appscootercopy.scooterusemicroservice.service.exception.PauseActiveException;
import com.appscootercopy.scooterusemicroservice.service.timer.TimerPause;
import com.appscootercopy.scooterusemicroservice.service.dto.scooterTrip.response.ScooterTripResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.request.TripRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.response.TripResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.exception.ConflictExistException;
import com.appscootercopy.scooterusemicroservice.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("TripService")
public class TripService {

    private TripRepository tripRepository;
    private ScooterTripRepository scooterTripRepository;
    private TariffRepository tariffRepository;
    private GeneralPriceRepository priceRepository;
    private ScooterRepository scooterRepository;
    private ScooterStopRepository stopRepository;

    public TripService(TripRepository t,ScooterTripRepository str, ScooterRepository sr,
                       TariffRepository tariffRepository, GeneralPriceRepository pr, ScooterStopRepository ssr) {
        this.tripRepository=t;
        this.scooterTripRepository=str;
        this.tariffRepository=tariffRepository;
        this.priceRepository=pr;
        this.scooterRepository=sr;
        this.stopRepository=ssr;
    }

    @Transactional(readOnly = true)
    public TripResponseDTO findTripById(long id) {
        return tripRepository.findById(id)
                .map(TripResponseDTO::new)
                .orElseThrow(() -> new NotFoundException("Trip", "Id", id));
    }

    @Transactional(readOnly = true)
    public List<TripResponseDTO> findAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(s1-> new TripResponseDTO(s1)).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity saveTrip(TripRequestDTO request) {
        if(!this.tripRepository.existsById(request.getId())){
            Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());
            GeneralPrice currentPrices = this.priceRepository.findByCurrent(true);
            if(currentPrices != null) {
                GeneralPrice lastPrices = this.priceRepository.findByLastDateValidity(currentPrices.getDateValidity());
                if(lastPrices == null || currentDate.compareTo(lastPrices.getDateValidity()) < 0) {
                    this.tripRepository.save(new Trip(request, currentPrices.getPriceService()));
                    return new ResponseEntity(request.getId(), HttpStatus.CREATED);
                }
                else {
                    lastPrices.setCurrent(true);
                    currentPrices.setCurrent(false);
                    this.tripRepository.save(new Trip(request, lastPrices.getPriceService()));
                    return new ResponseEntity(request.getId(), HttpStatus.CREATED);
                }
            }
            throw new NotFoundException("GeneralPrices", "current", "true");
        }

        throw new ConflictExistException("Trip", "ID", request.getId());
    }

    @Transactional
    public ResponseEntity updateTrip(TripRequestDTO request, Long id) {
        Optional<Trip> tripExisting = this.tripRepository.findById(id);
        if(!tripExisting.isEmpty()){
            if(this.tripRepository.existsById(request.getId())) {
                if(request.getId() == id) {
                    tripExisting.get().setKms(request.getKms());
                    tripExisting.get().setInitTime(request.getInitTime());
                    tripExisting.get().setEndTime(request.getEndTime());
                    tripExisting.get().setEnded(request.getEnded());
                    return new ResponseEntity(id, HttpStatus.ACCEPTED);
                }
                else {
                    throw new ConflictExistException("Trip", "Id", request.getId());
                }
            }
            throw new NotFoundException("Trip", "Id", request.getId());
        }
        throw new NotFoundException("Trip", "Id", id);
    }

    @Transactional
    public ResponseEntity finishTrip(FinishTripRequestDTO request, Long id) {
        Optional<Trip> tripExisting = this.tripRepository.findById(id);
        if(!tripExisting.isEmpty()){
            //trip existe
            String licensePlate = tripExisting.get().getLicenseScooterAssociated();
            Scooter s = this.scooterRepository.findByLicensePLate(licensePlate);
            ScooterStop stop = this.stopRepository.existsByUbicationXAndUbicationY(s.getUbication().getX(), s.getUbication().getY());
            if(stop != null) {
                //scooter en parada valida seteo valores de trip
                tripExisting.get().setEndTime(Timestamp.valueOf(LocalDateTime.now()));
                tripExisting.get().setKms(request.getKms());
                tripExisting.get().setEnded(request.getEnded());
                ScooterTripId idST = new ScooterTripId(s,tripExisting.get());
                if(!this.scooterTripRepository.existsById(idST)){
                    //no existe registro todavia
                    this.scooterTripRepository.save(new ScooterTrip(idST));
                }
                else {
                    throw new ConflictExistException("ScooterTrip", "IdScooter", s.getId(), "IdTrip", tripExisting.get().getId());
                }
                return new ResponseEntity(id, HttpStatus.ACCEPTED);
            }
            else {
                throw new InvalidScooterStopException("Scooter", "licensePlate", licensePlate);
            }
        }
        throw new NotFoundException("Trip", "Id", id);
    }

    @Transactional
    public ResponseEntity deleteTrip(Long id) {
        if(this.tripRepository.existsById(id)){
            ScooterTrip scooterTrip = this.scooterTripRepository.findById_IdTrip_Id(id);
            if(scooterTrip!=null) {
                this.scooterTripRepository.delete(scooterTrip);
            }
            this.tripRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            throw new NotFoundException("Trip", "Id", id);
        }
    }

    @Transactional(readOnly = true)
    public ScooterTripResponseDTO findScooterTripByTripId(Long id) {
        ScooterTrip scooterTrip = this.scooterTripRepository.findById_IdTrip_Id(id);
        if(scooterTrip!=null) {
            return new ScooterTripResponseDTO(scooterTrip);
        }
        throw new NotFoundException("ScooterTrip", "IdTrip", id);
    }

    @Transactional
    public ResponseEntity saveTariff(TariffRequestDTO request) {
        if(!this.tariffRepository.existsByType(request.getType())){
            this.tariffRepository.save(new Tariff(request.getCost(), request.getType()));
            return new ResponseEntity(request.getType(), HttpStatus.CREATED);
        }

        throw new ConflictExistException("Tariff", "Type", request.getType());
    }

    @Transactional
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void initPause(Long id) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if(!trip.isEmpty()) {
            PauseTrip pauseTrip = trip.get().getPause();
            if(pauseTrip == null) {
                trip.get().setTimer(new TimerPause(trip.get().getId(), this.tripRepository, this.tariffRepository, this.priceRepository));
                trip.get().getTimer().initPause();
            }
            else {
                throw new ConflictExistException("PauseTrip", "Id", pauseTrip.getId());
            }
        }
        else {
            throw new NotFoundException("Trip", "Id", id);
        }
    }

    @Transactional
    public ResponseEntity endPause(Long id) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if(!trip.isEmpty()) {
            PauseTrip pauseTrip = trip.get().getPause();
            if(pauseTrip != null) {
                if(pauseTrip.getEndPause() == null) {
                    Time endPause = Time.valueOf(LocalTime.now());
                    Time initPause = pauseTrip.getInitPause();
                    Long diff = endPause.getTime() - initPause.getTime();
                    pauseTrip.setTimePause(diff/1000);
                    pauseTrip.setEndPause(endPause);
                    this.tripRepository.save(trip.get());
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
                else {
                    throw new PauseActiveException("PauseTrip", "id", pauseTrip.getId());
                }
            }
            else {
                throw new NotFoundException("PauseTrip", "Id", pauseTrip.getId());
            }
        }
        else {
            throw new NotFoundException("Trip", "Id", id);
        }

    }

    @Transactional(readOnly = true)
    public List<ReportProfitsDTO> findProfitsByMonthsInYear(Long year) {
        return this.tariffRepository.findProfitsByMonthsInYear(year)
                .stream().map(p->new ReportProfitsDTO(p.getXmonth(), p.getXyear(), p.getTotalProfits()))
                .collect(Collectors.toList());
    }

    public ResponseEntity saveNewPrices(GeneralPriceRequestDTO request) {
        if(this.priceRepository.findByDateValidity(request.getDateValidity()) == null) {
            this.priceRepository.save(new GeneralPrice(request));
            return new ResponseEntity("date validity: "+request.getDateValidity(), HttpStatus.CREATED);
        }

        throw new ConflictExistException("GeneralPrice", "dateValidity", request.getDateValidity());
    }

    @Transactional(readOnly = true)
    public List<GeneralPriceResponseDTO> findHistoryPrices() {
        List<GeneralPrice> prices = priceRepository.findAll();
        return prices.stream().map(s1-> new GeneralPriceResponseDTO(s1)).collect(Collectors.toList());
    }
}
