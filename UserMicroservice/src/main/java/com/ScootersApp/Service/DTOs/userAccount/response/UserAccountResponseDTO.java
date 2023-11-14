package com.ScootersApp.Service.DTOs.userAccount.response;

import com.ScootersApp.domain.UserAccount;
import lombok.Data;

@Data
public class UserAccountResponseDTO {

    private UserAccountIDResponseDTO id;

    public UserAccountResponseDTO(UserAccount userAccount) {
        this.id = new UserAccountIDResponseDTO(userAccount.getId());
    }
}
