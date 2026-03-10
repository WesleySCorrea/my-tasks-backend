package com.wsc.my_tasks_backend.exception;

public record ApiSubError(
        String field,
        String message
) {}