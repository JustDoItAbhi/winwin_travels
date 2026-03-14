package com.data_api.data_api.exceptions;

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
