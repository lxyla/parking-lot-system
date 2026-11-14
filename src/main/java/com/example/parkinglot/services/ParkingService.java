package com.example.parkinglot.services;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ParkingService {

    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public List<ParkingSpot> getAvailableParkingSpots() {
        return parkingSpotRepository.findByOccupiedFalse();
    }
}
