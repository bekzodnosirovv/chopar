package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailHistoryDTO {
    private UUID id;
    private String email;
    private String title;
    private String message;
    private LocalDateTime createdDate;
}
