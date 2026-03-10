package com.wsc.my_tasks_backend.service.impl;

import jakarta.transaction.Transactional;
import com.wsc.my_tasks_backend.entity.User;
import org.springframework.stereotype.Service;
import com.wsc.my_tasks_backend.entity.PasswordResetToken;
import com.wsc.my_tasks_backend.mapper.PasswordResetTokenMapper;
import com.wsc.my_tasks_backend.service.PasswordResetTokenService;
import com.wsc.my_tasks_backend.repository.PasswordResetTokenRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final PasswordResetTokenRepository repository;

    public PasswordResetTokenServiceImpl(PasswordResetTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public PasswordResetToken createPasswordResetToken(User user, UUID token) {

        repository.deleteByUser(user);

        PasswordResetToken passwordResetToken = PasswordResetTokenMapper.create(user, token);

        return repository.save(passwordResetToken);
    }

    @Override
    public PasswordResetToken findByToken(UUID token) {

        PasswordResetToken passwordResetToken = repository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (passwordResetToken.isUsed()) {
            throw new RuntimeException("Token já utilizado");
        }

        if (passwordResetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        passwordResetToken.setUsed(true);

        return repository.save(passwordResetToken);
    }
}
