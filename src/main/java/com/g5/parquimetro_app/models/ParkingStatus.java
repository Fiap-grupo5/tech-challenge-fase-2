package com.g5.parquimetro_app.models;

public enum ParkingStatus {
    EXPIRED("EXPIRED"),
    IN_PROGRESS("IN_PROGRESS"),
    PAID("PAID");

    private final String status;

    ParkingStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
