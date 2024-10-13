package com.g5.parquimetro_app.dtos;

import com.g5.parquimetro_app.models.VehicleType;
import com.g5.parquimetro_app.models.ParkingStatus;
import com.g5.parquimetro_app.models.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class VehicleDTO {
    private String id;
    private String plateNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime endTime;

    private VehicleType type;
    private ParkingStatus status;
    private PaymentMethod paymentMethod;

    private String formattedAmountDue;   
    private int chosenHours;
}

