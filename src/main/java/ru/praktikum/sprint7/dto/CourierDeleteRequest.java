package ru.praktikum.sprint7.dto;

public class CourierDeleteRequest {
    private int id;

    public CourierDeleteRequest(int id) {
        this.id = id;
    }

    public CourierDeleteRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
