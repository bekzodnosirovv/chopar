package com.example.dto;

import com.example.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersDTO {
    private Integer id;
    private Integer profileId;
    private String address;
    private Integer numberOfProducts;
    private Integer price;
    private OrderStatus status;
    private LocalDateTime createdDate;
}
