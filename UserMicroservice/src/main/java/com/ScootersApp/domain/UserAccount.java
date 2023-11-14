package com.ScootersApp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
//@Embeddable
public class UserAccount {

    @EmbeddedId
    private UserAccountID id;

    public UserAccount(UserAccountID id) {
        this.id = id;
    }

    /*
    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId("id:user")
    @JoinColumn( name = "id:user")
    private User user;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId("id_account")
    @JoinColumn ( name = "id_account")
    private Account account;
    */
}
