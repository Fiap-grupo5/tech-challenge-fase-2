package com.g5.parquimetro_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.g5.parquimetro_app.services.VehicleService;
import com.g5.parquimetro_app.dtos.EndParkingRequestDTO;
import com.g5.parquimetro_app.dtos.StartParkingRequestDTO;
import com.g5.parquimetro_app.dtos.VehicleDTO;

@Tag(name = "Veículos", description = "Operações relacionadas ao estacionamento de veículos")
@RestController
@RequestMapping(value = "/")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/start")
    public VehicleDTO startParking(@RequestBody StartParkingRequestDTO request) {
        return vehicleService.startParking(request.getPlateNumber(), request.getType(), request.getChosenHours());
    }

    @PostMapping("/end")
    public VehicleDTO endParking(@RequestBody EndParkingRequestDTO request) {
        return vehicleService.endParking(request.getPlateNumber(), request.getPaymentMethod());
    }

    @GetMapping("/status/{plateNumber}")
    public VehicleDTO getVehicleStatus(@PathVariable String plateNumber) {
        return vehicleService.getVehicleStatus(plateNumber);
    }
}
