package com.wsc.my_tasks_backend.service;

import com.wsc.my_tasks_backend.entity.User;
import com.wsc.my_tasks_backend.entity.PasswordResetToken;

import java.util.UUID;

public interface PasswordResetTokenService {

    PasswordResetToken createPasswordResetToken(User user, UUID token);

    PasswordResetToken findByToken(UUID token);
}
