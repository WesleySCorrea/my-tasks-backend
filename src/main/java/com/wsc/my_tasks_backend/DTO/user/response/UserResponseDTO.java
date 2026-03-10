package com.wsc.my_tasks_backend.DTO.user.response;

import com.wsc.my_tasks_backend.enums.Role;

import java.time.LocalDateTime;

public record UserResponseDTO(

    Long id,
    String name,
    String surname,
    String email,
    Role role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
