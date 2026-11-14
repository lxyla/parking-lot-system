package com.example.parkinglot.controller;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.services.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    public ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    @GetMapping("/spots/available")
    public List<ParkingSpot> getAvailableParkingSpots() {
        return parkingService.getAvailableParkingSpots();
    }

}
