package com.appscootercopy.scooterusemicroservice.service;
import com.appscootercopy.scooterusemicroservice.domain.*;
import com.appscootercopy.scooterusemicroservice.repository.*;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.request.EnableScooterRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.request.ScooterRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.request.TripsAndYearRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.response.*;
import com.appscootercopy.scooterusemicroservice.service.dto.scooterStop.request.ScooterStopRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooterStop.response.ScooterStopResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.trip.ScooterByTripsYearResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.ubication.request.UbicationRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.ubication.response.UbicationResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ScooterService")
public class ScooterService {

    private ScooterRepository scooterRepository;
    private ScooterStopRepository scooterStopRepository;
    private UbicationRepository ubicationRepository;

    private WebClient.Builder webClient;

    @Autowired
    HttpServletRequest request;

    public ScooterService(ScooterRepository s, ScooterStopRepository ss,
                          UbicationRepository ur, WebClient.Builder webClient) {
        this.scooterRepository=s;
        this.scooterStopRepository=ss;
        this.ubicationRepository=ur;
        this.webClient = webClient;
    }

    @Transactional(readOnly = true)
    public ScooterResponseDTO findScooterByLicensePlate(String licensePlate) {
        Scooter scooterFound = scooterRepository.findByLicensePLate(licensePlate);
        if(scooterFound!=null) {
            return new ScooterResponseDTO(scooterFound);
        }

        throw new NotFoundException("Scooter", "licencePlate", licensePlate);
    }

    @Transactional(readOnly = true)
    public List<ScooterResponseDTO> findAllScooter() {
        List<Scooter> scooters = scooterRepository.findAll();
        return scooters.stream().map(s1-> new ScooterResponseDTO(s1)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ScooterResponseDTO> findAllScooterFetchingUbication() {
        List<Scooter> scooters = scooterRepository.findAllfetchingUbication();
        return scooters.stream().map(s1-> new ScooterResponseDTO(s1)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReportAvailabilityDTO findCountScooterByAvailability() {
        Long countAvailables = this.scooterRepository.findCountScootersAvailables(true).getCountScooters();
        Long countNotAvailables = this.scooterRepository.findCountScootersAvailables(false).getCountScooters();
        return new ReportAvailabilityDTO(countAvailables,countNotAvailables);
    }

    @Transactional(readOnly = true)
    public List<ScooterResponseDTO> findAllScooterCloseToMe(UbicationRequestDTO request) {
        if(request.getX() == null || request.getY() == null) throw new BadRequestException("Complete all parameters");
        List<Scooter> scooters = scooterRepository.findAllCloseToMe(request.getX(), request.getY(), 5.0, 5.0);
        return scooters.stream()
                .map(s1-> new ScooterResponseDTO(s1)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ScooterByTripsYearResponseDTO> findAllScooterByTripsAndYear(TripsAndYearRequestDTO request) {

        String token = this.request.getHeader(HttpHeaders.AUTHORIZATION);

        List<ScooterByTripsYearResponseDTO> list = (List<ScooterByTripsYearResponseDTO>) this.webClient.build()
                .method(HttpMethod.GET)
                .uri("http://trip-microservice/trip-microservice/api/trips/scooters/trips&year")
                .bodyValue(request)
                .headers(httpHeaders -> {httpHeaders.set("Authorization", token);})
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ScooterByTripsYearResponseDTO.class)
                .collectList()
                .block();

        return list;
    }

    @Transactional(readOnly = true)
    public List<ScooterResponseDTO> findAllScooterWithUbication() {
        List<Scooter> scooters = scooterRepository.findAllfetchingUbication();
        return scooters.stream()
                .map(s1-> new ScooterResponseDTO(s1)).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity saveScooter(ScooterRequestDTO request) {
        if(!this.scooterRepository.existsByLicensePLate(request.getLicensePlate())) {
            if(request.getUbication().getId() != null && !this.ubicationRepository.existsById(request.getUbication().getId())){
                if(request.getUbication().getX() == null || request.getUbication().getY() == null) throw  new BadRequestException(("Complete X and Y of Ubication"));
                this.scooterRepository.save(new Scooter(request, request.getUbication().getX(), request.getUbication().getY()));
            }else if (request.getUbication().getId() == null){
                this.scooterRepository.save(new Scooter(request));
            }else{
                throw new ConflictExistException("Ubication", "ID", request.getUbication().getId());
            }

            return new ResponseEntity(request.getLicensePlate(), HttpStatus.CREATED);
        }

        throw new ConflictExistException("Scooter", "licensePlate", request.getLicensePlate());
    }

    @Transactional
    public ResponseEntity deleteScooter(Long id){
        Optional<Scooter> scooter = this.scooterRepository.findById(id);
        if(scooter.isPresent()) {
            String licensePlate = scooter.get().getLicensePLate();
            String token = this.request.getHeader(HttpHeaders.AUTHORIZATION);
            this.webClient.build()
                    .delete()
                    .uri("http://trip-microservice/trip-microservice/api/trips/license-scooter/{licenseScooter}", licensePlate)
                    .headers(httpHeaders -> {httpHeaders.set("Authorization", token);})
                    .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                                    .bodyToMono(Void.class)
                                            .block();

            this.scooterRepository.deleteById(id);
            return new ResponseEntity(id, HttpStatus.OK);
        }
        else {
            throw new NotFoundException("Scooter", "Id", id);
        }
    }

    @Transactional
    public ResponseEntity updateScooter(ScooterRequestDTO request, Long idScooter) {
        Optional<Scooter> scooterExisting = this.scooterRepository.findById(idScooter);
        if(!scooterExisting.isEmpty()){
            Scooter scooter = scooterExisting.get();
            if(scooter.getLicensePLate().equals(request.getLicensePlate())) {
                scooter.setAvailable(request.getAvailable());
                Double x = request.getUbication().getX();
                Double y = request.getUbication().getY();
                if(x == null || y == null) throw new BadRequestException("Complete X and Y of Ubication");
                    scooter.getUbication().setX(request.getUbication().getX());
                    scooter.getUbication().setY(request.getUbication().getY());

                return new ResponseEntity(idScooter, HttpStatus.ACCEPTED);
            }
            else {
                throw new UniqueException("Scooter", "licensePlate", request.getLicensePlate());
            }
        }
        throw new NotFoundException("Scooter", "Id", idScooter);
    }

    @Transactional
    public ScooterResponseDTO enableScooter(EnableScooterRequestDTO request, Long id) {
        Optional<Scooter> scooterExisting = this.scooterRepository.findById(id);
        if(!scooterExisting.isEmpty()){
            scooterExisting.get().setAvailable(request.getAvailable());
            return new ScooterResponseDTO(scooterExisting.get());
        }
        throw new NotFoundException("Scooter", "Id", id);
    }

    @Transactional(readOnly = true)
    public List<ReportScootersDTO> findUseScootersByKms() {

        String token = this.request.getHeader(HttpHeaders.AUTHORIZATION);

        List<ReportScootersDTO> list = (List<ReportScootersDTO>) this.webClient.build()
                .get()
                .uri("http://trip-microservice/trip-microservice/api/trips/report/kms")
                .headers(httpHeaders -> {httpHeaders.set("Authorization", token);})
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ReportScootersDTO.class)
                .collectList()
                .block();

        return list;

    }

    @Transactional(readOnly = true)
    public List<ReportScootersDTO> findUseScootersByTimeCcPauses() {
        String token = this.request.getHeader(HttpHeaders.AUTHORIZATION);

        List<ReportScootersDTO> list = (List<ReportScootersDTO>) this.webClient.build()
                .get()
                .uri("http://trip-microservice/trip-microservice/api/trips/report/pauses")
                .headers(httpHeaders -> {httpHeaders.set("Authorization", token);})
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ReportScootersDTO.class)
                .collectList()
                .block();

        return list;
    }

    @Transactional(readOnly = true)
    public List<ReportScootersDTO> findUseScootersByTimeOutPauses() {
        String token = this.request.getHeader(HttpHeaders.AUTHORIZATION);

        List<ReportScootersDTO> list = (List<ReportScootersDTO>) this.webClient.build()
                .get()
                .uri("http://trip-microservice/trip-microservice/api/trips/report/non&pauses")
                .headers(httpHeaders -> {httpHeaders.set("Authorization", token);})
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ReportScootersDTO.class)
                .collectList()
                .block();
        return list;
    }

    @Transactional(readOnly = true)
    public ScooterStopResponseDTO findScooterStopByUbication(Long ubicationId) {
        ScooterStop scooterStopFound = scooterStopRepository.findByUbicationId(ubicationId);
        if(scooterStopFound!=null) {
            return new ScooterStopResponseDTO(scooterStopFound);
        }
        throw new NotFoundException("ScooterStop", "ubication.id", ubicationId);
    }

    @Transactional(readOnly = true)
    public List<ScooterStopResponseDTO> findAllScooterStop() {
        List<ScooterStop> stops = scooterStopRepository.findAll();
        return stops.stream().map(ss-> new ScooterStopResponseDTO(ss))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ScooterStopResponseDTO> findAllScooterStopWithUbication() {
        List<ScooterStop> stops = scooterStopRepository.findAllfetchingUbication();
        return stops.stream()
                .map(ss-> new ScooterStopResponseDTO(ss)).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity saveScooterStop(ScooterStopRequestDTO request) {
        Double x = request.getUbication().getX();
        Double y = request.getUbication().getY();
        if(x == null || y == null) throw new BadRequestException("Error set X and Y in Ubication");
        if(this.scooterStopRepository.existsByUbicationXAndUbicationY(x,y) == null) {
            this.scooterStopRepository.save(new ScooterStop(request));
            return new ResponseEntity("ubication: "+x+", "+y, HttpStatus.CREATED);
        }
        throw new ConflictExistException("ScooterStop", "ubicationX", x, "ubicationY", y);
    }

    @Transactional
    public ResponseEntity deleteScooterStop(Long idScooter) {
        if(this.scooterStopRepository.existsById(idScooter)) {
            this.scooterStopRepository.deleteById(idScooter);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        throw new NotFoundException("ScooterStop", "Id", idScooter);
    }

    @Transactional
    public ResponseEntity updateScooterStop(ScooterStopRequestDTO request, Long idScooterStop) {
        Optional<ScooterStop> scooterStopExisting = this.scooterStopRepository.findById(idScooterStop);
        if(!scooterStopExisting.isEmpty()){
            Double x = request.getUbication().getX();
            Double y = request.getUbication().getY();
            if(x == null || y == null) throw new BadRequestException("Error set X and Y in Ubication");
            if(this.scooterStopRepository.existsByUbicationXAndUbicationY(x,y) == null) {
                scooterStopExisting.get().getUbication().setX(x);
                scooterStopExisting.get().getUbication().setY(y);
                return new ResponseEntity(idScooterStop, HttpStatus.ACCEPTED);
            }
            else {
                throw new ConflictExistException("ScooterStop", "ubicationX", x, "ubicationY", y);
            }
        }
        throw new NotFoundException("ScooterStop", "Id", idScooterStop);
    }

    @Transactional(readOnly = true)
    public UbicationResponseDTO findUbicationById(Long id) {
        return ubicationRepository.findById(id)
                .map(UbicationResponseDTO::new)
                .orElseThrow(() -> new NotFoundException("Ubication", "Id", id));
    }

    @Transactional(readOnly = true)
    public List<UbicationResponseDTO> findAllUbication() {
        List<Ubication> ubications = ubicationRepository.findAll();
        return ubications.stream().map(u-> new UbicationResponseDTO(u)).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity checkScooterInStop(String licensePlate) {
        Scooter s = this.scooterRepository.findByLicensePLate(licensePlate);
        if(s != null) {
            ScooterStop ss = this.scooterStopRepository.existsByUbicationXAndUbicationY(s.getUbication().getX(), s.getUbication().getY());
            if(ss != null) {
                return new ResponseEntity(licensePlate, HttpStatus.ACCEPTED);
            }
            throw new InvalidScooterStopException("ScooterStop", "licensePlate", licensePlate);
        }
        throw new NotFoundException("scooter", "licensePlate", licensePlate);
    }
}
