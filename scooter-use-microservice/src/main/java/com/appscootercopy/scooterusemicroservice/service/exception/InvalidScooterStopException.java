package com.appscootercopy.scooterusemicroservice.service.exception;

import lombok.Getter;

@Getter
public class InvalidScooterStopException extends RuntimeException{

    private String message;
    public InvalidScooterStopException(String entity, String attribute, String licensePlate) {
        this.message = String.format("The %s with %s is not at a valid stop, with licensePlate: %s", entity, attribute, licensePlate);
    }
}
