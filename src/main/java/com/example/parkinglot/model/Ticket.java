package com.example.parkinglot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private ParkingSpot parkingSpot;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket() {}

    public Ticket(String licensePlate, VehicleType vehicleType, ParkingSpot parkingSpot){
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
    }

    public long getId(){ return id; }
    public String getLicensePlate() { return licensePlate; }
    public VehicleType getVehicleType() { return vehicleType; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public ParkingSpot getParkingSpot() { return parkingSpot; }
    public LocalDateTime getExitTime() { return exitTime; }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
}
