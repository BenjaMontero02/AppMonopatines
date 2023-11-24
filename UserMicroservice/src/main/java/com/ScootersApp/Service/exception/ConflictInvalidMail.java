package com.ScootersApp.Service.exception;

import lombok.Getter;

@Getter

public class ConflictInvalidMail extends RuntimeException{
    private String message;
    public ConflictInvalidMail(String mail) {
        this.message = String.format("the email does not have an @: ", mail);
    }
}
