package com.servepay_platform.exception;

public class PaymentException  extends RuntimeException{

    public PaymentException(String message)
    {
        super(message);
    }
}
