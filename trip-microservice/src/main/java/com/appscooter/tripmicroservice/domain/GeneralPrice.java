package com.appscooter.tripmicroservice.domain;

import com.appscooter.tripmicroservice.services.dtos.generalprice.request.GeneralPriceRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double priceService;
    @Column
    private Double priceInterest;
    @Column
    private Boolean current;
    @Column
    private Timestamp dateValidity;

    public GeneralPrice(GeneralPriceRequestDTO request) {
        this.priceService = request.getPriceService();
        this.priceInterest = request.getPriceInterest();
        this.current = request.getCurrent();
        this.dateValidity = request.getDateValidity();
    }

    public GeneralPrice(double service, double interest, Boolean current, Timestamp validity) {
        this.priceService = service;
        this.priceInterest = interest;
        this.current = current;
        this.dateValidity = validity;
    }
}
