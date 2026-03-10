package com.wsc.my_tasks_backend.exception.runtime;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }
}