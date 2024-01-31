package com.example.webfluxdemo1.dto;

public class InputFailedValidationError extends RuntimeException{

    private static final String MSG = "Allowed range is 10 - 20";
    private static final int errorCode = 100;
    private final int input;

    public InputFailedValidationError(int input) {
        super(MSG);
        this.input = input;
    }

    public int getInput() {
        return input;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
