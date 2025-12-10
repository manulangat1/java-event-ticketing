package com.example.ticket_platform.controllers;


import com.example.ticket_platform.domain.dtos.ErrorDto;
import com.example.ticket_platform.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<ErrorDto> handleUserNotFoundException (UserNotFoundException ex) {
        log.error("Caught user not found exception", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("User not found");
        return  new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArguementNotValidException (
MethodArgumentNotValidException ex
    ) {
        log.error("Caught MethodArgumentNotValidException ", ex);
        ErrorDto errorDto = new ErrorDto();
        BindingResult bindingResult= ex.getBindingResult();
       List<FieldError> fieldErrors= bindingResult.getFieldErrors();
        String errorMessage =  fieldErrors.stream().findFirst().map(
               fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()
       ).orElse("Validation error occurred.");
        errorDto.setError(errorMessage);
        return  new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( ConstraintViolationException.class)
    public  ResponseEntity<ErrorDto> handleConstraintViolation (ConstraintViolationException ex) {
        log.error("Caught ConstraintViolationException ", ex);
        ErrorDto errorDto = new ErrorDto();
       String errorMessage = ex.getConstraintViolations().stream().findFirst().map(violation -> violation.getPropertyPath() + ":" + violation.getMessage()
        ).orElse("Constraint violation occurred");
        errorDto.setError(errorMessage);
        return  new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleError ( Exception ex){
        log.error("Caught exception", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("An unkonw error occurend");
        return  new ResponseEntity<ErrorDto>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
