package com.example.parkinglot.model;

import jakarta.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public int spotsNeeded(){
        return switch(vehicleType){
            case CAR -> 1;
            case MOTORCYCLE -> 1;
            case BUS -> 2;
        };
    }
}
