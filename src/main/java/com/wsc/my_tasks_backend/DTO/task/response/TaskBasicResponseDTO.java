package com.wsc.my_tasks_backend.DTO.task.response;

import com.wsc.my_tasks_backend.enums.Status;
import com.wsc.my_tasks_backend.enums.Priority;

import java.time.LocalDate;

public record TaskBasicResponseDTO (
        Long id,
        String title,
        String description,
        Priority priority,
        Status status,
        LocalDate deadline
) {}