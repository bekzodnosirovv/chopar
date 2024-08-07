package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer price;
    private Integer productTypeId;
    private LocalDateTime localDateTime;
}
