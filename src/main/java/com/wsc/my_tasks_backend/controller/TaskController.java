package com.wsc.my_tasks_backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import com.wsc.my_tasks_backend.enums.Status;
import com.wsc.my_tasks_backend.enums.Priority;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.wsc.my_tasks_backend.service.TaskService;
import org.springframework.data.web.PageableDefault;
import com.wsc.my_tasks_backend.DTO.task.response.TaskResponseDTO;
import com.wsc.my_tasks_backend.DTO.task.request.UpdateTaskRrquestDTO;
import com.wsc.my_tasks_backend.DTO.task.request.CreateTaskRequestDTO;
import com.wsc.my_tasks_backend.DTO.task.response.TaskBasicResponseDTO;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskBasicResponseDTO>> getTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) LocalDate deadline,
            @PageableDefault(size = 10, sort = {"deadline", "createdAt"}, direction = Sort.Direction.ASC)
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                taskService.getTasks(title, priority, status, deadline, pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findByTaskId (@PathVariable Long id) {

        TaskResponseDTO task = taskService.taskInfoById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(task);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask (@RequestBody @Valid CreateTaskRequestDTO requestDTO) {

        TaskResponseDTO taskResponseDTO = taskService.createTask(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseDTO);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRrquestDTO request) {

        taskService.updateTask(id, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable Long id) {

        taskService.completeTask(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reopen")
    public ResponseEntity<Void> reopenTask(@PathVariable Long id) {

        taskService.reopenTask(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}
