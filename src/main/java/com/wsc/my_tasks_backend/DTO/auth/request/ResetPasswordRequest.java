package com.wsc.my_tasks_backend.DTO.auth.request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ResetPasswordRequest(

        @NotNull(message = "Token é um campo obrigatório")
        UUID token,
        @NotBlank(message = "Senha é um campo obrigatório")
        @Size(min = 6, max = 100, message = "Senha deve ter entre 6 e 100 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$",
                message = "Senha deve conter letra maiúscula, minúscula e número"
        )
        String newPassword
) {}
