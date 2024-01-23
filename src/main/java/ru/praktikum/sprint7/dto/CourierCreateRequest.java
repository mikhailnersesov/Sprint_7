package ru.praktikum.sprint7.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourierCreateRequest {
    private String login;
    private String password;
    private String firstName;
}
