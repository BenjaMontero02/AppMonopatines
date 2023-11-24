package com.appscooter.tripmicroservice.services.dtos.trip.responses;

import com.appscooter.tripmicroservice.domain.Trip;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TripResponseDTO {

    private Long id;
    private Timestamp initTime;
    private Timestamp endTime;
    private Double kms;
    private Boolean ended;
    private Long idPause;
    private Long idTariff;
    private Long idTariffExtra;
    private String licenseScooter;
    private String userEmail;

    public TripResponseDTO(Trip trip) {
        this.id=trip.getId();
        this.initTime=trip.getInitTime();
        this.endTime=trip.getEndTime();
        this.kms= trip.getKms();
        this.ended=trip.getEnded();
        this.licenseScooter=trip.getLicenseScooter();
        this.userEmail=trip.getUserEmail();
        if(trip.getPause() == null) {
            this.idPause=null;
        }
        else {
            this.idPause=trip.getPause().getId();
        }
        if(trip.getTariffExtra() == null) {
            this.idTariffExtra=null;
        }
        else {
            this.idTariffExtra=trip.getTariffExtra().getId();
        }
        this.idTariff=trip.getTariff().getId();
    }

}
