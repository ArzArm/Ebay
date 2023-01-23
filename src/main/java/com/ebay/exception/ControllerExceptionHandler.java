package com.ebay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> httpClientExceptionHandler(HttpClientErrorException ex) {

        return ResponseEntity.status(HttpStatus.valueOf(ex.getRawStatusCode())).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(MissingAccessTokenException.class)
    public ResponseEntity<ErrorMessage> failDataException(MissingAccessTokenException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                ex.statusCode,
                ex.getMessage(),
                request.getDescription(true));
        return ResponseEntity.status(ex.statusCode).body(errorMessage);
    }


}
