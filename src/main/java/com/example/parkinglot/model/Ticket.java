package com.example.parkinglot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToMany
    @JoinTable(
            name = "ticket_parking_spot",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "parking_spot_id")
    )
    private List<ParkingSpot> parkingSpots = new ArrayList<>();
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket() {}

    public Ticket(String licensePlate, VehicleType vehicleType,
                  List<ParkingSpot> parkingSpots) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.status = TicketStatus.ACTIVE;
        this.parkingSpots = new ArrayList<>(parkingSpots);
        this.entryTime = LocalDateTime.now();
    }

    public long getId(){ return id; }
    public String getLicensePlate() { return licensePlate; }
    public VehicleType getVehicleType() { return vehicleType; }
    public TicketStatus getStatus() { return status; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
    public LocalDateTime getExitTime() { return exitTime; }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
}
