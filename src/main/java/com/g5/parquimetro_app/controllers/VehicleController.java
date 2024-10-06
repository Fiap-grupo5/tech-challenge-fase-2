package com.g5.parquimetro_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.g5.parquimetro_app.models.Vehicle;
import com.g5.parquimetro_app.services.VehicleService;

@RestController
@RequestMapping(value = "/")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/start")
    public Vehicle startParking(@RequestParam String plateNumber){
        return vehicleService.startParking(plateNumber);
    }

    @PostMapping("/end")
    public Vehicle endParking(@RequestParam String plateNumber){
        return vehicleService.endParking(plateNumber);
    }
    
}