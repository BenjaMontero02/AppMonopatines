package com.appscootercopy.scooterusemicroservice.service.dto.trip.response;

import com.appscootercopy.scooterusemicroservice.domain.PauseTrip;
import com.appscootercopy.scooterusemicroservice.domain.Tariff;
import com.appscootercopy.scooterusemicroservice.domain.Trip;
import lombok.Data;

import java.io.Serializable;
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

    public TripResponseDTO(Trip trip) {
        this.id=trip.getId();
        this.initTime=trip.getInitTime();
        this.endTime=trip.getEndTime();
        this.kms= trip.getKms();
        this.ended=trip.getEnded();
        this.licenseScooter=trip.getLicenseScooterAssociated();
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
