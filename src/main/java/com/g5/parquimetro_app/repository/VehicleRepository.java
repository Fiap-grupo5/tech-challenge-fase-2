package com.g5.parquimetro_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.g5.parquimetro_app.models.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
