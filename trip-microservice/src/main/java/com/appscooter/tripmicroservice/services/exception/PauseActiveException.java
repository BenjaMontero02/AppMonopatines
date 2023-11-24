package com.appscooter.tripmicroservice.services.exception;

import lombok.Getter;

@Getter
public class PauseActiveException extends RuntimeException {

    private String message;

    public PauseActiveException(String entity, String typeID, Long id) {
        this.message = String.format("The entity %s with id %s %s, is active yet", entity, typeID, id);
    }
}
