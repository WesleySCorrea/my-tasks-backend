package com.wsc.my_tasks_backend.service;

import com.wsc.my_tasks_backend.entity.Task;
import org.springframework.data.domain.Page;
import com.wsc.my_tasks_backend.enums.Status;
import com.wsc.my_tasks_backend.enums.Priority;
import org.springframework.data.domain.Pageable;
import com.wsc.my_tasks_backend.DTO.task.response.TaskResponseDTO;
import com.wsc.my_tasks_backend.DTO.task.request.CreateTaskRequestDTO;
import com.wsc.my_tasks_backend.DTO.task.request.UpdateTaskRrquestDTO;
import com.wsc.my_tasks_backend.DTO.task.response.TaskBasicResponseDTO;

import java.time.LocalDate;

public interface TaskService {

    Page<TaskBasicResponseDTO> getTasks(
            String title,
            Priority priority,
            Status status,
            LocalDate deadline,
            Pageable pageable
    );

    TaskResponseDTO createTask (CreateTaskRequestDTO requestDTO);

    Task findById (Long id);

    TaskResponseDTO taskInfoById (Long id);

    void updateTask(Long id, UpdateTaskRrquestDTO request);

    void completeTask (Long id);

    void reopenTask (Long id);

    void deleteTask (Long id);
}
