package com.g5.parquimetro_app.models;

public enum PaymentMethod {
    CARD("CARD"),
    PIX("PIX");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return this.method;
    }
}
