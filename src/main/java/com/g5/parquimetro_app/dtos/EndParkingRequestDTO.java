package com.g5.parquimetro_app.dtos;

import com.g5.parquimetro_app.models.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EndParkingRequestDTO {
    @NotNull(message = "Campo de Placa obrigatório!")
    private String plateNumber;

    @NotNull(message = "Método de Pagamento obrigatório!")
    private PaymentMethod paymentMethod;
}
