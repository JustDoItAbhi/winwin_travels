package com.auth_api.auth_api.exceptions;

public class WinUserNotFountException extends RuntimeException{

    public WinUserNotFountException() {
    }

    public WinUserNotFountException(String message) {
        super(message);
    }

    public WinUserNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public WinUserNotFountException(Throwable cause) {
        super(cause);
    }

    public WinUserNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
