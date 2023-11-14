package com.ScootersApp.Service.exception;

import lombok.Getter;

@Getter
public class ConflictWithStatusException extends RuntimeException{
    private String message;

    public ConflictWithStatusException(String entity, String attribute, int status) {
        this.message = String.format("The status of this %s entity is already %s %s.", entity, attribute, status);
    }
}
