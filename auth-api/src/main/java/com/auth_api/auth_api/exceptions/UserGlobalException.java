package com.auth_api.auth_api.exceptions;

import com.auth_api.auth_api.exceptions.exceptiondto.Message;
import com.auth_api.auth_api.exceptions.exceptiondto.PasswordNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserGlobalException {
    @ExceptionHandler(WinUserNotFountException.class)
    public ResponseEntity<Message>userNotFount(WinUserNotFountException e){
        Message message=new Message(
                e.getMessage(),
                404,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<Message>psssNotFount(PasswordNotMatchException e){
        Message message=new Message(
                e.getMessage(),
                404,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IncorrectPathForServiceB.class)
    public ResponseEntity<Message>pathNotFount(IncorrectPathForServiceB e){
        Message message=new Message(
                e.getMessage(),
                502,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message,HttpStatus.BAD_GATEWAY);
    }
}
