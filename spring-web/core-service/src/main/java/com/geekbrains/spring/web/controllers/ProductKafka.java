package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.dto.OrderDetailsDto;
import com.geekbrains.spring.web.dto.OrderKafkaDto;
import com.geekbrains.spring.web.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafkatest")
public class ProductKafka {

    @Qualifier(value = "KafkaTest")
    @Autowired
    private KafkaTemplate<Long, OrderKafkaDto> kafkaTemplate;

    @PostMapping
    public void createOrderKafka(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName){
        ListenableFuture<SendResult<Long, OrderKafkaDto>> future = kafkaTemplate.send("TopicForOrders", new OrderKafkaDto(username, orderDetailsDto, cartName));
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

}
