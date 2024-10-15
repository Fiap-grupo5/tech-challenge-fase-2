package com.g5.parquimetro_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import com.g5.parquimetro_app.services.VehicleService;
import com.g5.parquimetro_app.dtos.EndParkingRequestDTO;
import com.g5.parquimetro_app.dtos.StartParkingRequestDTO;
import com.g5.parquimetro_app.dtos.VehicleDTO;

@Tag(name = "Veículos", description = "Operações relacionadas ao estacionamento de veículos")
@RestController
@RequestMapping(value = "/")
@Tag(name = "Veículos", description = "Veículos API") 
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/start")
    @Operation(summary = "Iniciar Estacionamento", description = "API para iniciar o estacionamento de um veículo. Tipo suportado: CAR ou MOTORCYCLE")
    public VehicleDTO startParking(@Valid @RequestBody StartParkingRequestDTO request) {
        return vehicleService.startParking(request.getPlateNumber(), request.getType(), request.getChosenHours());
    }


    @PostMapping("/end")
    @Operation(summary = "Finalizar Estacionamento", description = "API para finalizar o estacionamento de um veículo")
    public VehicleDTO endParking(@Valid @RequestBody EndParkingRequestDTO request) {
        return vehicleService.endParking(request.getPlateNumber(), request.getPaymentMethod());
    }

    @GetMapping("/status/{plateNumber}")
    @Operation(summary = "Status do Veículo", description = "API para obter o status de um veículo no estacionamento")
    public VehicleDTO getVehicleStatus(@PathVariable String plateNumber) {
        return vehicleService.getVehicleStatus(plateNumber);
    }
}
