package com.g5.parquimetro_app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Data
@Builder
@Jacksonized
@Document(collection = "vehicles")
public class Vehicle {
    @Id
    private String id;

    private String plateNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime endTime;

    private double amountDue;

    private VehicleType type;

    private ParkingStatus status;

    private PaymentMethod paymentMethod;

    private int chosenHours;

    @Version
    private Long version;

    @JsonGetter("formattedAmountDue")
    public String getFormattedAmountDue() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00", symbols);
        return decimalFormat.format(amountDue);
    }
}
