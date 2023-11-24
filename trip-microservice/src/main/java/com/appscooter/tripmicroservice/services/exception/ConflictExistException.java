package com.appscooter.tripmicroservice.services.exception;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ConflictExistException extends RuntimeException {

    private String message;
    public ConflictExistException(String entity, String attribute, Long id) {
        this.message = String.format("There is already a %s entity with %s %s.", entity, attribute, id);
    }

    public ConflictExistException(String entity, String attribute, Timestamp date) {
        this.message = String.format("There is already a %s entity with %s %s.", entity, attribute, date);
    }

    public String getMessage() {
        return message;
    }

}
