package com.wsc.my_tasks_backend.service.impl;

import com.wsc.my_tasks_backend.entity.User;
import org.springframework.stereotype.Service;
import com.wsc.my_tasks_backend.service.UserService;
import com.wsc.auth.lib.service.AuthenticationService;
import com.wsc.my_tasks_backend.repository.UserRepository;
import com.wsc.my_tasks_backend.DTO.user.response.UserResponseDTO;
import com.wsc.my_tasks_backend.DTO.user.request.CreateUserRequestDTO;
import com.wsc.my_tasks_backend.exception.runtime.ResourceNotFoundException;

import static com.wsc.my_tasks_backend.mapper.UserMapper.toEntity;
import static com.wsc.my_tasks_backend.mapper.UserMapper.toResponseDTO;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authService;

    public UserServiceImpl(UserRepository userRepository, AuthenticationService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO requestDTO) {

        User user = toEntity(requestDTO);
        user.setPassword(authService.generateHashPassword(requestDTO.password()));

        user = userRepository.save(user);

        return toResponseDTO(user);
    }

    @Override
    public User findUserOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Usuário não encontrado"));
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Usuário não encontrado"));
    }

    @Override
    public void updatePassword(User user, String newPassword) {

        user.setPassword(authService.generateHashPassword(newPassword));

        userRepository.save(user);
    }
}
