package com.wsc.my_tasks_backend.exception.runtime;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}