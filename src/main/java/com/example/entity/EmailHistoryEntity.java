package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_history")
@Getter
@Setter
public class EmailHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "to_email")
    private String toEmail;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
