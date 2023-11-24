package com.appscootercopy.scooterusemicroservice.service.exception;

public class BadRequestException extends RuntimeException {

    private String message;
    public BadRequestException(String completeAllParameters) {
        this.message = completeAllParameters;
    }

    public String getMessage() {
        return message;
    }
}
