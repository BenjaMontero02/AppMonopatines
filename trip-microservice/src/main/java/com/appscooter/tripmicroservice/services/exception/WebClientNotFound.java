package com.appscooter.tripmicroservice.services.exception;

import lombok.Data;

@Data
public class WebClientNotFound extends RuntimeException{

    private String message;

    public WebClientNotFound(String message){
        this.message = message;
    }
}
