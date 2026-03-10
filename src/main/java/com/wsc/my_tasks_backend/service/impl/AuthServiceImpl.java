package com.wsc.my_tasks_backend.service.impl;

import jakarta.transaction.Transactional;
import com.wsc.auth.lib.model.AuthResponse;
import com.wsc.my_tasks_backend.entity.User;
import org.springframework.stereotype.Service;
import com.wsc.my_tasks_backend.mapper.AuthMapper;
import com.wsc.my_tasks_backend.service.AuthService;
import com.wsc.my_tasks_backend.service.UserService;
import com.wsc.my_tasks_backend.service.EmailService;
import com.wsc.auth.lib.service.AuthenticationService;
import com.wsc.my_tasks_backend.entity.PasswordResetToken;
import com.wsc.my_tasks_backend.DTO.auth.request.LoginRequest;
import com.wsc.my_tasks_backend.DTO.auth.response.LoginResponse;
import com.wsc.my_tasks_backend.service.PasswordResetTokenService;
import com.wsc.my_tasks_backend.DTO.auth.request.LoginRefreshRequest;
import com.wsc.my_tasks_backend.DTO.auth.request.ResetPasswordRequest;
import com.wsc.my_tasks_backend.DTO.auth.request.ForgotPasswordRequest;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final EmailService emailService;
    private final AuthenticationService authenticationService;
    private final PasswordResetTokenService passwordResetTokenService;

    public AuthServiceImpl(UserService userService, EmailService emailService, AuthenticationService authenticationService, PasswordResetTokenService passwordResetTokenService) {
        this.userService = userService;
        this.emailService = emailService;
        this.authenticationService = authenticationService;
        this.passwordResetTokenService = passwordResetTokenService;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userService.findByEmail(request.email().trim().toLowerCase());

        AuthResponse auth = authenticationService.authenticate(request.password().trim(), user.getPassword(), user);

        return AuthMapper.toResponseDTO(auth);
    }

    @Override
    public LoginResponse loginWithRefresh(LoginRefreshRequest request) {

        AuthResponse auth = authenticationService.authenticateWithRefreshToken(request.refreshToken());

        return AuthMapper.toResponseDTO(auth);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {

        User user = userService.findByEmail(request.email().trim().toLowerCase());

        UUID token = UUID.randomUUID();

        PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetToken(user, token);

        emailService.sendEmail(passwordResetToken);
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordRequest request) {

        PasswordResetToken passwordResetToken = passwordResetTokenService.findByToken(request.token());

        userService.updatePassword(passwordResetToken.getUser(), request.newPassword());
    }
}
