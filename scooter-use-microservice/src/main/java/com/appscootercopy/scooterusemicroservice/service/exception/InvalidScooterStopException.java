package com.appscootercopy.scooterusemicroservice.service.exception;

public class InvalidScooterStopException extends RuntimeException{

    private String message;
    public InvalidScooterStopException(String entity, String attribute, String licensePlate) {
        this.message = String.format("The Scooter with %s %s is not at a valid stop", entity, attribute, licensePlate);
    }
}
