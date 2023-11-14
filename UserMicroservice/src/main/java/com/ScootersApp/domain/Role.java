package com.ScootersApp.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    private String type;
    public Role(String type) {
        this.type = type;
    }
}
