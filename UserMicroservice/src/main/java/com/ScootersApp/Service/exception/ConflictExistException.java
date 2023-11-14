package com.ScootersApp.Service.exception;

import com.ScootersApp.domain.UserAccountID;

public class ConflictExistException extends RuntimeException {

    private String message;
    public ConflictExistException(String entity, String attribute, Long id) {
        this.message = String.format("There is already a %s entity with %s %s.", entity, attribute, id);
    }

    public ConflictExistException(String entity, String entity2, String attribute, String attribute2, Long id, Long id2){
        this.message = String.format("There is already a %s entity with %s, %s and there is already a %s entity with %s, %s", entity, attribute, id, entity2, attribute2, id2);
    }

    public ConflictExistException(String entity, String attribute, String mail) {
        this.message = String.format("There is already a %s entity with %s %s.", entity, attribute, mail);
    }

    public ConflictExistException(String entity, String attribute, UserAccountID userAccountID) {
        this.message = String.format("There is already a %s entity with %s %s.", entity, attribute, userAccountID);
    }

    public String getMessage() {
        return message;
    }

}
