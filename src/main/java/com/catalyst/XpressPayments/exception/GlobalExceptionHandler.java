package com.catalyst.XpressPayments.exception;

import com.catalyst.XpressPayments.utils.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<APIError> handleException(InvalidRequestException e){
        e.printStackTrace();
        return ResponseEntity.badRequest().body(APIError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getLocalizedMessage())
                .build());
    }
    

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIError> handleException(NotFoundException e){
        e.printStackTrace();
        return ResponseEntity.badRequest().body(APIError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Not Found!!")
                .build());
    }

}

