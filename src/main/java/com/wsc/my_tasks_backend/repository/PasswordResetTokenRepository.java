package com.wsc.my_tasks_backend.repository;

import com.wsc.my_tasks_backend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import com.wsc.my_tasks_backend.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(UUID token);

    @Modifying
    @Query("""
        DELETE FROM PasswordResetToken t
        WHERE t.expiresAt < CURRENT_TIMESTAMP
        OR t.used = true
    """)
    void updateInvalidTokens();

    void deleteByUser(User user);
}
