package com.etiya.common.entities.crosscuttingconcerns.exceptions.types;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
