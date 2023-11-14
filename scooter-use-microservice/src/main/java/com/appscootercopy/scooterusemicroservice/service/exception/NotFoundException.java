package com.appscootercopy.scooterusemicroservice.service.exception;

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

    public NotFoundException(String entity, String typeA, Double x, String typeB, Double y) {
        this.message = String.format("The entity %s is not found with %s %s, %s %s.", entity, typeA, x, typeB, y);
    }

    public NotFoundException(String entity, String typeA, String typeB, Long idA, Long idB) {
        this.message = String.format("The entity %s is not found with %s %s, %s %s.", entity,typeA,idA,typeB,idB);
    }
}
