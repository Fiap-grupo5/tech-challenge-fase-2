package com.g5.parquimetro_app.dtos;

import com.g5.parquimetro_app.models.VehicleType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StartParkingRequestDTO {
    @NotNull(message = "Campo de Placa obrigatório!")
    private String plateNumber;

    @NotNull(message = "Campo de tipo obrigatório!")
    private VehicleType type;

    @NotNull(message = "Campo de data de fechamento é obrigatório!")
    @Min(value = 1, message = "Horas devem ser no mínimo 1!")
    @Max(value = 9, message = "Horas devem ser no máximo 9!")
    private int chosenHours;
}
