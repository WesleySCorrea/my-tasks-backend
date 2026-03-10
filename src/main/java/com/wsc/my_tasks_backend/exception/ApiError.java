package com.wsc.my_tasks_backend.exception;

import java.util.List;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
        List<ApiSubError> errors
) {}
