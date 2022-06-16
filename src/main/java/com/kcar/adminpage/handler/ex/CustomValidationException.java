package com.kcar.adminpage.handler.ex;

public class CustomValidationException extends RuntimeException{

    public CustomValidationException(String message) {
        super(message);
    }
}
