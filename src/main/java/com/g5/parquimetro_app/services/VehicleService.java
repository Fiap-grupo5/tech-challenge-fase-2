package com.g5.parquimetro_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.g5.parquimetro_app.models.Vehicle;
import com.g5.parquimetro_app.models.VehicleType;
import com.g5.parquimetro_app.models.ParkingStatus;
import com.g5.parquimetro_app.models.PaymentMethod;
import com.g5.parquimetro_app.repository.VehicleRepository;
import com.g5.parquimetro_app.dtos.VehicleDTO;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    private static final double CAR_RATE_PER_HOUR = 6.50;
    private static final double MOTORCYCLE_RATE_PER_HOUR = 4.50;
    private static final double ADDITIONAL_CHARGE_PER_MINUTE = 0.60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int MAX_HOURS = 9;

    public VehicleDTO startParking(String plateNumber, VehicleType type, int chosenHours) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findByPlateNumberAndStatus(plateNumber, ParkingStatus.IN_PROGRESS);
        
        if (existingVehicle.isPresent()) {
            throw new IllegalStateException("Este veículo já está em um estacionamento ativo.");
        }
    
        if (chosenHours < 1 || chosenHours > MAX_HOURS) {
            throw new IllegalArgumentException("O número de horas deve estar entre 1 e " + MAX_HOURS);
        }
    
        double amountDue = calculateAmountDue(type, chosenHours);
        LocalDateTime simulatedStartTime = LocalDateTime.now().minusHours(3);
    
        Vehicle vehicle = Vehicle.builder()
                .plateNumber(plateNumber)
                .startTime(simulatedStartTime)
                .type(type)
                .amountDue(amountDue)
                .status(ParkingStatus.IN_PROGRESS)
                .chosenHours(chosenHours)
                .build();
        
        vehicleRepository.save(vehicle);
        
        return VehicleDTO.builder()
                .id(vehicle.getId())
                .plateNumber(vehicle.getPlateNumber())
                .startTime(vehicle.getStartTime())
                .type(vehicle.getType())
                .status(vehicle.getStatus())
                .paymentMethod(vehicle.getPaymentMethod() != null ? vehicle.getPaymentMethod() : null)
                .formattedAmountDue(vehicle.getFormattedAmountDue())
                .chosenHours(chosenHours)
                .build();
    }

    private double calculateAmountDue(VehicleType type, int chosenHours) {
        double ratePerHour = type == VehicleType.CAR ? CAR_RATE_PER_HOUR : MOTORCYCLE_RATE_PER_HOUR;
        return chosenHours * ratePerHour;
    }

    public VehicleDTO endParking(String plateNumber, PaymentMethod paymentMethod) {
        Vehicle vehicle = vehicleRepository.findByPlateNumberAndStatus(plateNumber, ParkingStatus.IN_PROGRESS)
            .orElseThrow(() -> new IllegalStateException("Veículo não encontrado com status IN_PROGRESS: " + plateNumber));
        
        vehicle.setEndTime(LocalDateTime.now().minusHours(3));
        vehicle.setPaymentMethod(paymentMethod);
    
        Duration parkingDuration = Duration.between(vehicle.getStartTime(), vehicle.getEndTime());
        long totalMinutes = parkingDuration.toMinutes();
        int hoursUsed = (int) totalMinutes / MINUTES_IN_HOUR;
        long extraMinutes = totalMinutes % MINUTES_IN_HOUR;
    
        double totalAmountDue = calculateAmountDue(vehicle.getType(), vehicle.getChosenHours());
        if (hoursUsed > vehicle.getChosenHours()) {
            totalAmountDue += (hoursUsed - vehicle.getChosenHours()) * ADDITIONAL_CHARGE_PER_MINUTE * MINUTES_IN_HOUR + (extraMinutes * ADDITIONAL_CHARGE_PER_MINUTE);
        }
        
        vehicle.setAmountDue(totalAmountDue);
        vehicle.setStatus(ParkingStatus.PAID);
        
        vehicleRepository.save(vehicle);
        
        return VehicleDTO.builder()
                .id(vehicle.getId())
                .plateNumber(vehicle.getPlateNumber())
                .startTime(vehicle.getStartTime())
                .endTime(vehicle.getEndTime())
                .type(vehicle.getType())
                .status(vehicle.getStatus())
                .paymentMethod(vehicle.getPaymentMethod() != null ? vehicle.getPaymentMethod() : null)
                .formattedAmountDue(vehicle.getFormattedAmountDue())
                .chosenHours(vehicle.getChosenHours())
                .build();
    }

         
    public VehicleDTO getVehicleStatus(String plateNumber) {
        Vehicle vehicle = vehicleRepository.findByPlateNumberAndStatus(plateNumber, ParkingStatus.IN_PROGRESS)
            .orElseThrow(() -> new IllegalStateException("Veículo não encontrado ou já encerrado: " + plateNumber));
    
        LocalDateTime currentTime = LocalDateTime.now().minusHours(3);
        Duration parkingDuration = Duration.between(vehicle.getStartTime(), currentTime);
        long totalMinutes = parkingDuration.toMinutes();
        
        int hoursUsed = (int) totalMinutes / MINUTES_IN_HOUR;
        long extraMinutes = totalMinutes % MINUTES_IN_HOUR;
        
        double totalAmountDue = calculateAmountDue(vehicle.getType(), vehicle.getChosenHours());
        if (hoursUsed > vehicle.getChosenHours()) {
            totalAmountDue += (hoursUsed - vehicle.getChosenHours()) * ADDITIONAL_CHARGE_PER_MINUTE * MINUTES_IN_HOUR + (extraMinutes * ADDITIONAL_CHARGE_PER_MINUTE);
        }
        
        vehicle.setStatus(hoursUsed > vehicle.getChosenHours() ? ParkingStatus.EXPIRED : ParkingStatus.IN_PROGRESS);
        vehicle.setAmountDue(totalAmountDue);
        
        return VehicleDTO.builder()
                .id(vehicle.getId())
                .plateNumber(vehicle.getPlateNumber())
                .startTime(vehicle.getStartTime())
                .endTime(null)
                .type(vehicle.getType())
                .status(vehicle.getStatus())
                .paymentMethod(vehicle.getPaymentMethod() != null ? vehicle.getPaymentMethod() : null)
                .formattedAmountDue(vehicle.getFormattedAmountDue())
                .chosenHours(vehicle.getChosenHours())
                .build();
    }
    
}