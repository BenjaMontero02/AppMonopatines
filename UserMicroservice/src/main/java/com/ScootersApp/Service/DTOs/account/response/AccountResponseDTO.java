package com.ScootersApp.Service.DTOs.account.response;

import com.ScootersApp.domain.Account;
import lombok.Data;
import java.util.Date;

@Data
public class AccountResponseDTO {

    private Long id;
    private Double wallet;
    private Date dateUp;

    public AccountResponseDTO(Account a) {
        this.id=a.getId();
        this.wallet=a.getWallet();
        this.dateUp=a.getDateUp();
    }


}
