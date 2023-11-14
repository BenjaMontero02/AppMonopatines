package com.ScootersApp.Service.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private String message;
    public NotFoundException(String entity, String typeID, Long id) {
        this.message = String.format("The entity %s is not found with %s %s", entity, typeID, id);
    }

    public NotFoundException(String entity, String typeID, Long id, Long id2) {
        this.message = String.format("The entity %s is not found with %s  user: %s, account: %s", entity, typeID, id, id2);
    }

    public NotFoundException(String entity, String typeID, String mail) {
        this.message = String.format("The entity %s is not found with %s %s", entity, typeID, mail);
    }
}
