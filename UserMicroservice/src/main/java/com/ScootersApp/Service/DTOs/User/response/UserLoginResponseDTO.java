package com.ScootersApp.Service.DTOs.User.response;

import com.ScootersApp.domain.User;
import lombok.Data;

@Data
public class UserLoginResponseDTO {
        private final String mail;
        private final String password;
        private final String role;

        public UserLoginResponseDTO(User u) {
            this.mail = u.getMail();
            this.password = u.getPassword();
            this.role = u.getRole();
        }
}
