package com.g5.parquimetro_app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@Document(collection = "vehicles")
public class Vehicle {
    @Id
    String id;
    String placa;
    //...
}
