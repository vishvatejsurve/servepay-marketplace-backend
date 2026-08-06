package com.servepay_platform.exception;

public class AccessDeniedException  extends RuntimeException{

    public AccessDeniedException(String message)
    {
        super(message);
    }
}
