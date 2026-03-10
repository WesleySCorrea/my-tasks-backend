package com.wsc.my_tasks_backend.repository;

import com.wsc.my_tasks_backend.entity.Task;
import org.springframework.data.domain.Page;
import com.wsc.my_tasks_backend.enums.Status;
import com.wsc.my_tasks_backend.enums.Priority;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
       SELECT t FROM Task t
       WHERE t.user.id = :userId
       AND (:title IS NULL OR t.title ILIKE :title)
       AND (:priority IS NULL OR t.priority = :priority)
       AND (:status IS NULL OR t.status = :status)
    """)
    Page<Task> findTasks(
            @Param("userId") Long userId,
            @Param("title") String title,
            @Param("priority") Priority priority,
            @Param("status") Status status,
            Pageable pageable
    );

    @Query("""
       SELECT t FROM Task t
       WHERE t.user.id = :userId
       AND (:title IS NULL OR t.title ILIKE :title)
       AND (:priority IS NULL OR t.priority = :priority)
       AND (:status IS NULL OR t.status = :status)
       AND t.deadline = :deadline
    """)
    Page<Task> findTasksWithDeadline(
            @Param("userId") Long userId,
            @Param("title") String title,
            @Param("priority") Priority priority,
            @Param("status") Status status,
            @Param("deadline") LocalDate deadline,
            Pageable pageable
    );

    @Modifying
    @Query("""
        UPDATE Task t
        SET t.status = 'ATRASADA'
        WHERE t.deadline < CURRENT_DATE
          AND t.status = 'ABERTA'
    """)
    void updateTaskStatus();
}
