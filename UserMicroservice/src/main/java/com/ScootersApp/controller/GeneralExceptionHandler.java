package com.ScootersApp.controller;

import com.ScootersApp.Service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
@RestControllerAdvice(basePackages = "com.ScootersApp.controller")
public class GeneralExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundException(NotFoundException exception) {
        return new ResponseEntity(new ErrorDTO(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictExistException.class)
    public ResponseEntity conflictExistException(ConflictExistException exception) {
        return new ResponseEntity(new ErrorDTO(exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //MethodArgumentNotValidException, receives the exception of validations
    public ResponseEntity constraintViolationException(MethodArgumentNotValidException exc){

        /*first I get the BindingResult. second I get the list of FieldError*/
        List<String> errors = exc.getBindingResult().getFieldErrors().stream().map( f -> f.getDefaultMessage()).toList();
        /*for each FieldError, I get the defaultMessage which is a string and add it to the error list*/

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReferencedRowException.class)
    public ResponseEntity conflictExistException(ReferencedRowException exception) {
        return new ResponseEntity(new ErrorDTO(exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConflictWithStatusException.class)
    public ResponseEntity conflictExistException(ConflictWithStatusException exception) {
        return new ResponseEntity(new ErrorDTO(exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConflictInvalidMail.class)
    public ResponseEntity conflictExistException(ConflictInvalidMail exception) {
        return new ResponseEntity(new ErrorDTO(exception.getMessage()), HttpStatus.CONFLICT);
    }
}