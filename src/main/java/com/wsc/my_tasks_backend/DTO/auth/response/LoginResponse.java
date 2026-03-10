package com.wsc.my_tasks_backend.DTO.auth.response;

public record LoginResponse (

    String accessToken,
    String refreshToken,
    String tokenType,
    Long expiresIn,
    String userName
) {}
