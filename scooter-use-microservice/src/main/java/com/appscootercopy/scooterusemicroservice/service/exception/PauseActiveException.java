package com.appscootercopy.scooterusemicroservice.service.exception;

public class PauseActiveException extends RuntimeException {

    private String message;

    public PauseActiveException(String entity, String typeID, Long id) {
        this.message = String.format("The entity %s with id %s %s, is active yet", entity, typeID, id);
    }
}
