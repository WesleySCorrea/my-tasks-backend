package com.wsc.my_tasks_backend.mapper;

import com.wsc.my_tasks_backend.entity.User;
import com.wsc.my_tasks_backend.entity.PasswordResetToken;

import java.time.LocalDateTime;
import java.util.UUID;

public class PasswordResetTokenMapper {

    public static PasswordResetToken create(User user, UUID token) {

        PasswordResetToken entity = new PasswordResetToken();
        LocalDateTime now = LocalDateTime.now();

        entity.setToken(token);
        entity.setUser(user);
        entity.setCreatedAt(now);
        entity.setExpiresAt(now.plusMinutes(15));

        return entity;
    }
}
