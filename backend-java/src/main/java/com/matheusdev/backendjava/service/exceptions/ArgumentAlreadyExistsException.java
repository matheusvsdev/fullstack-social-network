package com.matheusdev.backendjava.service.exceptions;

public class ArgumentAlreadyExistsException extends RuntimeException {

    public ArgumentAlreadyExistsException(String msg) {
        super(msg);
    }
}
