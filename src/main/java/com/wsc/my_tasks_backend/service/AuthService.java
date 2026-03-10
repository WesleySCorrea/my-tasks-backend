package com.wsc.my_tasks_backend.service;

import com.wsc.my_tasks_backend.DTO.auth.request.LoginRequest;
import com.wsc.my_tasks_backend.DTO.auth.response.LoginResponse;
import com.wsc.my_tasks_backend.DTO.auth.request.LoginRefreshRequest;
import com.wsc.my_tasks_backend.DTO.auth.request.ResetPasswordRequest;
import com.wsc.my_tasks_backend.DTO.auth.request.ForgotPasswordRequest;

public interface AuthService {

    LoginResponse login (LoginRequest request);

    LoginResponse loginWithRefresh (LoginRefreshRequest request);

    void forgotPassword(ForgotPasswordRequest request);

    void resetPassword(ResetPasswordRequest request);
}
