package com.data_api.data_api.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserGlobalException {
    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity<Message> invalidtoken(InvalidToken e){
        Message message=new Message(
                e.getMessage(),
                403,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message,HttpStatus.FORBIDDEN);
    }
}
