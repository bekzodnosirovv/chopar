package com.example.repository;

import com.example.entity.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity, Integer> {
    List<EmailHistoryEntity> findByEmail(String email);

    List<EmailHistoryEntity> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to);

    Page<EmailHistoryEntity> findAllBy(Pageable pageable);
}
