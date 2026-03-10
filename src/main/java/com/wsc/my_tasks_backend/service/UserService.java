package com.wsc.my_tasks_backend.service;

import com.wsc.my_tasks_backend.entity.User;
import com.wsc.my_tasks_backend.DTO.user.response.UserResponseDTO;
import com.wsc.my_tasks_backend.DTO.user.request.CreateUserRequestDTO;

public interface UserService {

    UserResponseDTO createUser(CreateUserRequestDTO requestDTO);

    User findUserOrThrow (Long id);

    User findByEmail (String email);

    void updatePassword(User user, String newPassword);
}
