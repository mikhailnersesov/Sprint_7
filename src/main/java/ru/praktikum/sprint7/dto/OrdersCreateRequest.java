package ru.praktikum.sprint7.dto;

import java.util.List;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersCreateRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;
}
