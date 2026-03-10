package com.wsc.my_tasks_backend.service;

import com.wsc.my_tasks_backend.entity.PasswordResetToken;

public interface EmailService {

    void sendEmail(PasswordResetToken passwordResetToken);
}
