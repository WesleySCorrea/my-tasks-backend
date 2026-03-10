package com.wsc.my_tasks_backend.mapper;

import com.wsc.my_tasks_backend.entity.Task;
import com.wsc.my_tasks_backend.entity.User;
import com.wsc.my_tasks_backend.DTO.task.response.TaskResponseDTO;
import com.wsc.my_tasks_backend.DTO.task.request.CreateTaskRequestDTO;
import com.wsc.my_tasks_backend.DTO.task.response.TaskBasicResponseDTO;

public class TaskMapper {

    public static Task toEntity (CreateTaskRequestDTO requestDTO, User user) {

        Task task = new Task();
        task.setTitle(requestDTO.title());
        task.setDescription(requestDTO.description());
        task.setPriority(requestDTO.priority());
        task.setDeadline(requestDTO.deadline());
        task.setUser(user);

        return task;
    }

    public static TaskResponseDTO toResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getStatus(),
                task.getDeadline(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getUser().getName()
        );
    }

    public static TaskBasicResponseDTO toBasicResponseDTO(Task task) {
        return new TaskBasicResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getStatus(),
                task.getDeadline()
        );
    }

    public static TaskResponseDTO toTaskResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getStatus(),
                task.getDeadline(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getUser().getName()
        );
    }
}