package com.appscooter.tripmicroservice.services.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private String message;
    public NotFoundException(String entity, String typeID, Long id) {
        this.message = String.format("The entity %s is not found with %s %s", entity, typeID, id);
    }

    public NotFoundException(String entity, String typeID, String id) {
        this.message = String.format("The entity %s is not found with %s %s", entity, typeID, id);
    }

}
