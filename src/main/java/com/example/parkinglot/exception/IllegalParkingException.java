package com.example.parkinglot.exception;

import com.example.parkinglot.model.ParkingSpot;

import java.time.LocalDateTime;

public class IllegalParkingException extends RuntimeException {
    public IllegalParkingException(String message) {
        super(message);
    }
}
