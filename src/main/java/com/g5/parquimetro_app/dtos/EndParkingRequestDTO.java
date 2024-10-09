package com.g5.parquimetro_app.dtos;

import com.g5.parquimetro_app.models.PaymentMethod;
import lombok.Data;

@Data
public class EndParkingRequestDTO {
    private String plateNumber;
    private PaymentMethod paymentMethod;
}
