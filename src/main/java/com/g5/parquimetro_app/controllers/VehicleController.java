package com.g5.parquimetro_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g5.parquimetro_app.models.Vehicle;
import com.g5.parquimetro_app.services.VehicleService;

@RestController
@RequestMapping(value = "/")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping(path = "/vehicles")
    public ResponseEntity<List<Vehicle>> getAllUsers() {
        return ResponseEntity.ok().body(vehicleService.getUsers());
    }

    @GetMapping(path = "/vehicles/{id}")
    public ResponseEntity<Vehicle> getUserByIdPath(@PathVariable String id) {
        return ResponseEntity.ok().body(vehicleService.getVehicleById(id));
    }

    @PostMapping(path = "/vehicles")
    public ResponseEntity<Vehicle> saveUser(@RequestBody Vehicle user) {
        return ResponseEntity.ok(vehicleService.saveVehicle(user));
    }
    
}