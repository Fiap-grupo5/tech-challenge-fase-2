package com.g5.parquimetro_app.dtos;

import com.g5.parquimetro_app.models.VehicleType;
import lombok.Data;

@Data
public class StartParkingRequestDTO {
    private String plateNumber;
    private VehicleType type;
    private int chosenHours;
}
