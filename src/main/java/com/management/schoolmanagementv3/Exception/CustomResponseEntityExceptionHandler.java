package com.management.schoolmanagementv3.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {

        CustomExpectionResponse customExpectionResponse = new CustomExpectionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(customExpectionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) throws Exception {

        CustomExpectionResponse customExpectionResponse = new CustomExpectionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(customExpectionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public final ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) throws Exception {

        CustomExpectionResponse customExpectionResponse = new CustomExpectionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(customExpectionResponse, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomExpectionResponse customExpectionResponse = new CustomExpectionResponse(new Date(), "Validation Failed :(", ex.getBindingResult().toString());

        return new ResponseEntity(customExpectionResponse, HttpStatus.BAD_REQUEST);
    }

}
