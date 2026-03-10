package com.wsc.my_tasks_backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.wsc.my_tasks_backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wsc.my_tasks_backend.DTO.user.response.UserResponseDTO;
import com.wsc.my_tasks_backend.DTO.user.request.CreateUserRequestDTO;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid CreateUserRequestDTO request) {

        UserResponseDTO userResponse = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
