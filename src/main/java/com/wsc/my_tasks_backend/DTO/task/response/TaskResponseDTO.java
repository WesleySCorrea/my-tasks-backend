package com.wsc.my_tasks_backend.DTO.task.response;

import com.wsc.my_tasks_backend.enums.Status;
import com.wsc.my_tasks_backend.enums.Priority;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        Priority priority,
        Status status,
        LocalDate deadline,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String userName
) {}
