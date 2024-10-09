package com.g5.parquimetro_app.repository;

import com.g5.parquimetro_app.models.Vehicle;
import com.g5.parquimetro_app.models.ParkingStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    Optional<Vehicle> findByPlateNumberAndStatus(String plateNumber, ParkingStatus status);
}