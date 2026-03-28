package com.example.parkinglot.model;

import jakarta.persistence.*;

@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int floorNumber;
    private int rowNumber;
    private int spotNumber;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    private boolean occupied;

    public ParkingSpot(){}

    public ParkingSpot(int floorNumber, int rowNumber, int spotNumber, SpotType spotType, boolean occupied){
        this.floorNumber = floorNumber;
        this.rowNumber = rowNumber;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.occupied = occupied;
    }

    public Long getId() { return id; }
    public int getFloorNumber() { return floorNumber; }
    public int getRowNumber() { return rowNumber; }
    public int getSpotNumber() { return spotNumber; }
    public SpotType getSpotType() { return spotType; }
    public boolean isOccupied() { return occupied; }

    public void setOccupied(boolean occupied) { this.occupied = occupied; }

}
