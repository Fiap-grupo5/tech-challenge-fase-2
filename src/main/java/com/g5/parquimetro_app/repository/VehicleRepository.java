package com.g5.parquimetro_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.g5.parquimetro_app.models.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    Optional<Vehicle> findByPlateNumber(String plateNumber);
}
