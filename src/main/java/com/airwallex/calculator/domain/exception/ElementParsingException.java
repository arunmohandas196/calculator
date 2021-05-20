package com.airwallex.calculator.domain.exception;

public class ElementParsingException extends RuntimeException {
    public ElementParsingException(String message) {
        super(message);
    }

    public ElementParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
