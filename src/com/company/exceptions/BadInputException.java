package com.company.exceptions;

public class BadInputException extends RuntimeException {
    public BadInputException(String you_gave_wrong_input_type) {
        super(you_gave_wrong_input_type);
    }
}
