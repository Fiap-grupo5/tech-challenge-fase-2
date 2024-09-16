package com.g5.parquimetro_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g5.parquimetro_app.models.Vehicle;
import com.g5.parquimetro_app.repository.VehicleRepository;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository userRepository;

    public List<Vehicle> getUsers() {
        return userRepository.findAll();
    }

    public Vehicle getVehicleById(String id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No vehicle by ID: " + id));
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return userRepository.save(vehicle);
    }
}
