package com.appiciel.accountservice.exception;

public class AmountNegativeException extends Throwable {
    public AmountNegativeException(String message) {
        super(message);
    }
}
