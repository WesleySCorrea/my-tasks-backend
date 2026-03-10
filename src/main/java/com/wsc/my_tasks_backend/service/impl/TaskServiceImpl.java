package com.wsc.my_tasks_backend.service.impl;

import com.wsc.my_tasks_backend.entity.Task;
import com.wsc.my_tasks_backend.entity.User;
import org.springframework.data.domain.Page;
import com.wsc.my_tasks_backend.enums.Status;
import org.springframework.stereotype.Service;
import com.wsc.my_tasks_backend.enums.Priority;
import org.springframework.data.domain.Pageable;
import com.wsc.my_tasks_backend.mapper.TaskMapper;
import com.wsc.my_tasks_backend.service.UserService;
import com.wsc.my_tasks_backend.service.TaskService;
import com.wsc.my_tasks_backend.repository.TaskRepository;
import com.wsc.my_tasks_backend.security.utils.SecurityUtils;
import com.wsc.my_tasks_backend.DTO.task.response.TaskResponseDTO;
import com.wsc.my_tasks_backend.exception.runtime.ForbiddenException;
import com.wsc.my_tasks_backend.DTO.task.request.CreateTaskRequestDTO;
import com.wsc.my_tasks_backend.DTO.task.request.UpdateTaskRrquestDTO;
import com.wsc.my_tasks_backend.DTO.task.response.TaskBasicResponseDTO;
import com.wsc.my_tasks_backend.exception.runtime.ResourceNotFoundException;

import java.time.LocalDate;

@Service
public class TaskServiceImpl implements TaskService {

    private final UserService userService;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserService userService, TaskRepository taskRepository) {
        this.userService = userService;
        this.taskRepository = taskRepository;
    }

    @Override
    public Page<TaskBasicResponseDTO> getTasks(
            String title,
            Priority priority,
            Status status,
            LocalDate deadline,
            Pageable pageable) {

        Long userId = SecurityUtils.getCurrentUserId();

        title = formatTitle(title);

        Page<Task> tasks;
        if (deadline == null) {
            tasks = taskRepository.findTasks(userId, title, priority, status, pageable);
        } else {
            tasks = taskRepository.findTasksWithDeadline(userId, title, priority, status, deadline, pageable);
        }

        return tasks.map(TaskMapper::toBasicResponseDTO);
    }

    @Override
    public TaskResponseDTO createTask(CreateTaskRequestDTO requestDTO) {

        Long userId = SecurityUtils.getCurrentUserId();

        User user = userService.findUserOrThrow(userId);

        Task task = taskRepository.save(TaskMapper.toEntity(requestDTO, user));

        return TaskMapper.toResponseDTO(task);
    }

    @Override
    public Task findById(Long id) {

        return taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tarefa não encontrada"));
    }

    @Override
    public TaskResponseDTO taskInfoById(Long id) {

        Long userId = SecurityUtils.getCurrentUserId();

        Task task =  this.findById(id);

        this.validateOwner(userId, task.getUser().getId());

        return TaskMapper.toTaskResponseDTO(task);
    }

    @Override
    public void updateTask(Long id, UpdateTaskRrquestDTO request) {

        Long userId = SecurityUtils.getCurrentUserId();

        Task task =  this.findById(id);

        this.validateOwner(userId, task.getUser().getId());

        task.setDescription(request.description());
        task.setPriority(request.priority());
        task.setDeadline(request.deadline());

        if (!task.getStatus().equals(Status.CONCLUIDA)) {
            this.validateStatus(task);
        }

        taskRepository.save(task);
    }

    @Override
    public void completeTask(Long id) {

        Long userId = SecurityUtils.getCurrentUserId();

        Task task = this.findById(id);

        this.validateOwner(userId, task.getUser().getId());

        task.setStatus(Status.CONCLUIDA);

        taskRepository.save(task);
    }

    @Override
    public void reopenTask(Long id) {

        Long userId = SecurityUtils.getCurrentUserId();

        Task task = this.findById(id);

        this.validateOwner(userId, task.getUser().getId());

        this.validateStatus(task);

        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {

        Long userId = SecurityUtils.getCurrentUserId();

        Task task = this.findById(id);

        this.validateOwner(userId, task.getUser().getId());

        taskRepository.delete(task);
    }

    private String formatTitle (String title) {

        if (title == null || title.isBlank()) {
            title = null;
        } else {
            title = "%" + title + "%";
        }

        return title;
    }

    private void validateOwner(Long userId, Long taskUserId) {

        if (!userId.equals(taskUserId)) {
            throw new ForbiddenException("Apenas o dono da tarefa pode altera-la");
        }
    }

    private void validateStatus(Task task) {

        LocalDate today = LocalDate.now();

        if (task.getDeadline().isBefore(today)) {
            task.setStatus(Status.ATRASADA);
        } else {
            task.setStatus(Status.ABERTA);
        }

    }
}
