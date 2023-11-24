package com.appscooter.tripmicroservice.services.dtos.generalprice.response;

import com.appscooter.tripmicroservice.domain.GeneralPrice;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class GeneralPriceResponseDTO {

    private Double priceService;
    private Double priceInterest;
    private Boolean current;
    private Timestamp dateValidity;

    public GeneralPriceResponseDTO(GeneralPrice g) {
        this.priceService = g.getPriceService();
        this.priceInterest = g.getPriceInterest();
        this.current = g.getCurrent();
        this.dateValidity = g.getDateValidity();
    }

}
