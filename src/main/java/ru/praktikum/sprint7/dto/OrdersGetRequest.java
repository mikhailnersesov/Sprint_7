package ru.praktikum.sprint7.dto;

public class OrdersGetRequest {
    private int courierId;
    private int limit;
    private String nearestStation;
    private int page;

    public OrdersGetRequest(int courierId, int limit, String nearestStation, int page) {
        this.courierId = courierId;
        this.limit = limit;
        this.nearestStation = nearestStation;
        this.page = page;
    }

    public OrdersGetRequest() {
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
