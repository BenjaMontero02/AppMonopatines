package com.appscootercopy.scooterusemicroservice.service.dto.ubication.response;

import com.appscootercopy.scooterusemicroservice.domain.Ubication;
import lombok.Data;

@Data
public class UbicationResponseDTO {

    private Long id;
    private Double x;
    private Double y;

    public UbicationResponseDTO(Ubication ubication) {
        this.id = ubication.getId();
        this.x = ubication.getX();
        this.y = ubication.getY();
    }


}
