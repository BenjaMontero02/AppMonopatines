package com.appscootercopy.scooterusemicroservice.service.exception;

import lombok.Getter;

@Getter
public class UniqueException extends RuntimeException {
    private String message;
    public UniqueException(String entity, String type, String attribute) {
        this.message = String.format("The attribute %s in the entity %s, is unique, cannot be modified with %s", type, entity, attribute);
    }
}
