package com.appscootercopy.scooterusemicroservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double cost;
    @Column
    private Long type;

    public Tariff(Double cost, Long type) {
        this.cost=cost;
        this.type=type;
    }
}
