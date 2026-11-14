package com.example.parkinglot.services;

import com.example.parkinglot.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ParkingService {

    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }
}
