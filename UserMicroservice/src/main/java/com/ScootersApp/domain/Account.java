package com.ScootersApp.domain;

import com.ScootersApp.Service.DTOs.account.request.AccountRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    private Long id;
    @Column
    private Double wallet;
    @Column
    private Timestamp dateUp;


    public Account(AccountRequestDTO requestDTO) {
        this.id = requestDTO.getId();
        this.wallet = requestDTO.getWallet();
        this.dateUp = Timestamp.valueOf(LocalDateTime.now()); //put the inmediat date time
    }

    public Account(Long id, Double wallet, Timestamp dateUp) {
        this.id = id;
        this.dateUp = dateUp;
        this.wallet = wallet;
    }
}
