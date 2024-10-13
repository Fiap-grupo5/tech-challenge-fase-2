package com.g5.parquimetro_app.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusRequestDTO {
    @NotNull(message = "Campo de Placa obrigatório!")
    private String plateNumber;
}
