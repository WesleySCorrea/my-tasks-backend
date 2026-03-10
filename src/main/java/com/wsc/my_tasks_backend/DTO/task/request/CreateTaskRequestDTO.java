package com.wsc.my_tasks_backend.DTO.task.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import com.wsc.my_tasks_backend.enums.Priority;

import java.time.LocalDate;

public record CreateTaskRequestDTO(

    @NotBlank(message = "Nome é um campo obrigatório")
    String title,
    String description,
    @NotNull(message = "Prioridade é um campo obrigatório")
    Priority priority,
    @NotNull(message = "Prazo é um campo obrigatório")
    LocalDate deadline
) {}
