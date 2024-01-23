package ru.praktikum.sprint7.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourierLoginRequest {
    private String login;
    private String password;
}
