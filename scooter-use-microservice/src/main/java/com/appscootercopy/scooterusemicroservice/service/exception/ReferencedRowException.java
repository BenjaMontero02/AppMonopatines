package com.appscootercopy.scooterusemicroservice.service.exception;

import lombok.Getter;

@Getter
public class ReferencedRowException extends RuntimeException {
    private String message;
    public ReferencedRowException(String entity, String entity2, String typeID, Long id) {
        this.message = String.format("The entity %s, with the id %s %s is referenced in entity %s, violates the foreign key", entity, typeID, id, entity2);
    }
}
