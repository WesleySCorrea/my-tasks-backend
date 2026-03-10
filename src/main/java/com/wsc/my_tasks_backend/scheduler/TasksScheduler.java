package com.wsc.my_tasks_backend.scheduler;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import com.wsc.my_tasks_backend.repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TasksScheduler {

    private final TaskRepository taskRepository;

    public TasksScheduler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void updateTaskStatusScheduler() {
        taskRepository.updateTaskStatus();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);

        System.out.println("\n\n------------------------------------------------------------------------------\n");
        System.out.println("Scheduler executado!");
        System.out.println("Scheduler: Tarefas ABERTAS vencidas atualizadas para ATRASADA!");
        System.out.println("Horário: " + now);
        System.out.println("\n------------------------------------------------------------------------------\n\n");
    }
}
