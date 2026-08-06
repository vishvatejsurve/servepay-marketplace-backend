package com.servepay_platform.exception;

public class BadCredentialException extends RuntimeException{

    public BadCredentialException(String message)
    {
        super(message);
    }
}
