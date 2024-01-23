package ru.praktikum.sprint7.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersGetRequest {
    private int courierId;
    private int limit;
    private String nearestStation;
    private int page;
}
