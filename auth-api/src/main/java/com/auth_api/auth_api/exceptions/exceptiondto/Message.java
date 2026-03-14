package com.auth_api.auth_api.exceptions.exceptiondto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private String message;
    int code;
    private LocalDateTime time;

    public Message(String message, int code, LocalDateTime time) {
        this.message = message;
        this.code = code;
        this.time = time;
    }
}
