package com.readingisgood.ordermanagement.exception;

import com.readingisgood.ordermanagement.adapter.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException(BadCredentialsException e) {
        logger.error("An BadCredentialsException occurred! Details: ", e);
        ApiResponse response = new ApiResponse(false, "Credential are wrong");
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        logger.error("An error occurred! Details: ", e);
        ApiResponse response = new ApiResponse(false, "Something went wrong");
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
