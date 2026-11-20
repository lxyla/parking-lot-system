package com.example.parkinglot.dto;

import com.example.parkinglot.model.VehicleType;

public record ParkVehicleRequest(
        String licensePlate,
        VehicleType vehicleType
) {
}