package com.wsc.my_tasks_backend.DTO.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é um campo obrigatório")
        String email
) {}