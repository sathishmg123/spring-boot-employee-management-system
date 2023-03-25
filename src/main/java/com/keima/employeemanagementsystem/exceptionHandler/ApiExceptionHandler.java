package com.keima.employeemanagementsystem.exceptionHandler;

import com.keima.employeemanagementsystem.exceptionHandler.exception.EntityFindingException;
import com.keima.employeemanagementsystem.exceptionHandler.exception.LoginException;
import com.keima.employeemanagementsystem.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { LoginException.class })
    public ResponseEntity<ExceptionResponse> handleLoginException(LoginException loginException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(loginException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { EntityFindingException.class })
    public ResponseEntity<ExceptionResponse> handleEntityException(EntityFindingException entityFindingException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(entityFindingException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
