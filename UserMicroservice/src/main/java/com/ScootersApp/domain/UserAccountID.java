package com.ScootersApp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class UserAccountID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "Fk_user_UserAccount"))
    private User user;
    @ManyToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "FK_account_UserAccount"))
    private Account account;

    public UserAccountID(User user, Account account) {
        super();
        this.user=user;
        this.account=account;
    }
}
