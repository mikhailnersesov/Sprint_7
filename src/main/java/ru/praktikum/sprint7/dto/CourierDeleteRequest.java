package ru.praktikum.sprint7.dto;

public class CourierDeleteRequest {
    private Integer id;

    public CourierDeleteRequest(Integer id) {
        this.id = id;
    }

    public CourierDeleteRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
