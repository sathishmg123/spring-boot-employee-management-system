package com.keima.employeemanagementsystem.exceptionHandler.exception;

public class EntityFindingException extends RuntimeException {

    public EntityFindingException(String message) {
        super(message);
    }

    public EntityFindingException(String message, Throwable cause) {
        super(message, cause);
    }
}
