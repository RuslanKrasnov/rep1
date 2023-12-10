package com.geekbrains.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderKafkaDto {

    private String username;
    private OrderDetailsDto orderDetailsDto;
    private String cartName;

}
