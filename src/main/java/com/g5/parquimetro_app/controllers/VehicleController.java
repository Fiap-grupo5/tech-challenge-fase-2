package com.g5.parquimetro_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.g5.parquimetro_app.services.VehicleService;
import com.g5.parquimetro_app.dtos.EndParkingRequestDTO;
import com.g5.parquimetro_app.dtos.StartParkingRequestDTO;
import com.g5.parquimetro_app.dtos.StatusRequestDTO;
import com.g5.parquimetro_app.dtos.VehicleDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/")
@Tag(name = "Vehicle", description = "Vehicle API") 
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/start")
    @Operation(summary = "Start parking", description = "Start parking for a vehicle")
    public VehicleDTO startParking(@RequestBody StartParkingRequestDTO request) {
        return vehicleService.startParking(request.getPlateNumber(), request.getType(), request.getChosenHours());
    }


    @PostMapping("/end")
    @Operation(summary = "End parking", description = "End parking for a vehicle")
    public VehicleDTO endParking(@RequestBody EndParkingRequestDTO request) {
        return vehicleService.endParking(request.getPlateNumber(), request.getPaymentMethod());
    }

    @GetMapping("/status")
    public VehicleDTO getVehicleStatus(@RequestBody StatusRequestDTO request) {
        return vehicleService.getVehicleStatus(request.getPlateNumber());
    }
}
