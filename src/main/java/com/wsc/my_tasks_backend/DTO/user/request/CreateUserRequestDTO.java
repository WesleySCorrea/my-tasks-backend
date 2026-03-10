package com.wsc.my_tasks_backend.DTO.user.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDTO(

    @NotBlank(message = "Nome é um campo obrigatório")
    String name,

    @NotBlank(message = "Sobrenome é um campo obrigatório")
    String surName,

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é um campo obrigatório")
    String email,

    @NotBlank(message = "Senha é um campo obrigatório")
    @Size(min = 6, max = 100, message = "Senha deve ter entre 6 e 100 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$",
            message = "Senha deve conter letra maiúscula, minúscula e número"
    )
    String password
) {}
