package com.geekbrains.spring.web.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CartApp {
    // Домашнее задание:
    // Реализовать перехватчик методов удаления и
    // *Вывести пользователя, который залогинился
    public static void main(String[] args) {
        SpringApplication.run(CartApp.class, args);
    }
}
