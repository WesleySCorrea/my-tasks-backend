package com.wsc.my_tasks_backend.mapper;

import com.wsc.auth.lib.model.AuthResponse;
import com.wsc.my_tasks_backend.DTO.auth.response.LoginResponse;

public class AuthMapper {

    public static LoginResponse toResponseDTO (AuthResponse response) {

        return new LoginResponse(
                response.accessToken(),
                response.refreshToken(),
                response.tokenType(),
                response.expiresAt(),
                response.userInfo().getName()
        );
    }
}
