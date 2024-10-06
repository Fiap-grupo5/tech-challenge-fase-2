package com.g5.parquimetro_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g5.parquimetro_app.models.Vehicle;
import com.g5.parquimetro_app.repository.VehicleRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle startParking(String plateNumber){
        Vehicle vehicle = Vehicle.builder()
                .plateNumber(plateNumber)
                .startTime(LocalDateTime.now())
                .build();

        return vehicleRepository.save(vehicle);
    }

    public Vehicle endParking(String plateNumber){
        Vehicle vehicle =
                vehicleRepository.findByPlateNumber(plateNumber)
                        .orElseThrow(() -> new RuntimeException("Veículo não " +
                                "encontrado: " + plateNumber));

        vehicle.setEndTime(LocalDateTime.now());

        Duration parkingDuration = Duration.between(vehicle.getStartTime(),
                vehicle.getEndTime());
        double amountDue = calculateAmountDue(parkingDuration);
        vehicle.setAmountDue(amountDue);

        return vehicleRepository.save(vehicle);
    }

    private double calculateAmountDue(Duration parkingDuration) {
        long minutes = parkingDuration.toMinutes();
        return minutes * 0.60;
    }
}
