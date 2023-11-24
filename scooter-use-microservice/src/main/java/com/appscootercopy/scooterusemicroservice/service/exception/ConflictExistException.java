package com.appscootercopy.scooterusemicroservice.service.exception;

import java.sql.Time;
import java.sql.Timestamp;

public class ConflictExistException extends RuntimeException {

    private String message;

    public ConflictExistException(String entity, String attribute, Double x, String attribute2, Double y) {
        this.message = String.format("There is already a %s entity with %s %s, and %s %s.", entity, attribute, x, attribute2, y);
    }

    public ConflictExistException(String entity, String attribute, String licensePLate) {
        this.message = String.format("There is already a %s entity with %s %s.", entity, attribute, licensePLate);
    }

    public ConflictExistException(String entity, String attribute, Long id) {
        this.message = String.format("There is already a %s entity with %s %s.", entity, attribute, id);
    }

    public String getMessage() {
        return message;
    }

}
