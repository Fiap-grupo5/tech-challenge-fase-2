package com.g5.parquimetro_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.g5.parquimetro_app.models.Vehicle;
import com.g5.parquimetro_app.services.VehicleService;

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
    public Vehicle startParking(@RequestParam String plateNumber){
        return vehicleService.startParking(plateNumber);
    }

    @PostMapping("/end")
    @Operation(summary = "End parking", description = "End parking for a vehicle")
    public Vehicle endParking(@RequestParam String plateNumber){
        return vehicleService.endParking(plateNumber);
    }
    
}