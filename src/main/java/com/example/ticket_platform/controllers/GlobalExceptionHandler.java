package com.example.ticket_platform.controllers;


import com.example.ticket_platform.domain.dtos.ErrorDto;
import com.example.ticket_platform.exceptions.*;
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

    @ExceptionHandler(EventNotFoundException.class)
    public  ResponseEntity<ErrorDto> handleEventNotFoundException (EventNotFoundException ex) {
        log.error("Caught event not found exception", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("event not found");
        return  new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( QrCodeNotFoundException.class)
    public  ResponseEntity<ErrorDto> handleQrCodeNotFoundException (QrCodeNotFoundException ex) {
        log.error("Caught qr code not found exception", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("qr code not found");
        return  new ResponseEntity<ErrorDto>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TicketTypeNotFoundException.class)
    public  ResponseEntity<ErrorDto> handleTicketTypeNotFoundException (TicketTypeNotFoundException ex) {
        log.error("Caught ticket type not found exception", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("ticket type not found");
        return  new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventUpdateException.class)
    public  ResponseEntity<ErrorDto> handleEventUpdateException (EventUpdateException ex) {
        log.error("Caught Event Update exception", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Event Update");
        return  new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }



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
