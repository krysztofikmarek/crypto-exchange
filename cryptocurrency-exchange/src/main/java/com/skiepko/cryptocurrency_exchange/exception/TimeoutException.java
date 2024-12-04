package com.skiepko.cryptocurrency_exchange.exception;

public class TimeoutException extends RuntimeException{
    public TimeoutException(String msg) {
        super(msg);
    }
}
