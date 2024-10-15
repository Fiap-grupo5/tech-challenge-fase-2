package com.g5.parquimetro_app.models;

public enum VehicleType {
    CAR("CAR"),
    MOTORCYCLE("MOTORCYCLE");

    private final String type;

    VehicleType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
