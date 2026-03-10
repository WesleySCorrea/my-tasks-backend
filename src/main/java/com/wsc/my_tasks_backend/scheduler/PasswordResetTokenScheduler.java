package com.wsc.my_tasks_backend.scheduler;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import com.wsc.my_tasks_backend.repository.PasswordResetTokenRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PasswordResetTokenScheduler {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetTokenScheduler(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Transactional
    @Scheduled(cron = "1 0 0 * * *")
    public void updateTaskStatusScheduler() {
        passwordResetTokenRepository.updateInvalidTokens();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);

        System.out.println("\n\n------------------------------------------------------------------------------\n");
        System.out.println("Scheduler executado!");
        System.out.println("Scheduler: Tokens EXPIRADOS ou USADOS deletados!");
        System.out.println("Horário: " + now);
        System.out.println("\n------------------------------------------------------------------------------\n\n");
    }
}
