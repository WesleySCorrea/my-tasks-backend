package com.wsc.my_tasks_backend.DTO.auth.request;

public record LoginRefreshRequest(
        String refreshToken
) {}
