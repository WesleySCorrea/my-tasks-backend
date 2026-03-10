package com.wsc.my_tasks_backend.mapper;

import com.wsc.my_tasks_backend.enums.Role;
import com.wsc.my_tasks_backend.entity.User;
import com.wsc.my_tasks_backend.mapper.util.StringUtil;
import com.wsc.my_tasks_backend.DTO.user.response.UserResponseDTO;
import com.wsc.my_tasks_backend.DTO.user.request.CreateUserRequestDTO;

public class UserMapper {

    public static User toEntity (Long id) {
        User user = new User();
        user.setId(id);

        return user;
    }

    public static User toEntity (CreateUserRequestDTO requestDTO) {
        User user = new User();
        user.setName(StringUtil.normalizeName(requestDTO.name().trim()));
        user.setSurname(StringUtil.normalizeName(requestDTO.surName().trim()));
        user.setEmail(requestDTO.email().trim().toLowerCase());
        user.setPassword(requestDTO.password().trim());
        user.setRole(Role.USER);

        return user;
    }

    public static UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getSurName(),
                user.getEmail(),
                user.getRoleEnum(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
