package com.g5.parquimetro_app.models;

public enum ParkingStatus {
    EXPIRED("expired"),
    IN_PROGRESS("in progress"),
    PAID("paid");

    private final String status;

    ParkingStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
