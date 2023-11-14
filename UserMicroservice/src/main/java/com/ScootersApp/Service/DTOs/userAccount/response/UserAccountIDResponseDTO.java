package com.ScootersApp.Service.DTOs.userAccount.response;

import com.ScootersApp.domain.Account;
import com.ScootersApp.domain.User;
import com.ScootersApp.domain.UserAccountID;
import lombok.Data;

@Data
public class UserAccountIDResponseDTO {

    private User user;
    private Account account;

    public UserAccountIDResponseDTO(UserAccountID userAccountID) {
        this.user = userAccountID.getUser();
        this.account = userAccountID.getAccount();
    }
}
