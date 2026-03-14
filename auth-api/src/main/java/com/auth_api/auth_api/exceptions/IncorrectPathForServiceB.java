package com.auth_api.auth_api.exceptions;

public class IncorrectPathForServiceB extends RuntimeException{
    public IncorrectPathForServiceB() {
    }

    public IncorrectPathForServiceB(String message) {
        super(message);
    }

    public IncorrectPathForServiceB(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPathForServiceB(Throwable cause) {
        super(cause);
    }

    public IncorrectPathForServiceB(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
