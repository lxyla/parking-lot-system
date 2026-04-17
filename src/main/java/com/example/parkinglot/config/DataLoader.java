package com.example.parkinglot.config;


import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.SpotType;
import com.example.parkinglot.repository.ParkingpSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ParkingpSpotRepository parkingpSpotRepository;

    @Override
    public void run(String... args) {
        if(parkingpSpotRepository.count() == 0) {
            parkingpSpotRepository.saveAll(List.of(
                    new ParkingSpot(1, 1, 1, SpotType.COMPACT,   false),  // motorcycle spot
                    new ParkingSpot(1, 1, 2, SpotType.COMPACT,   false),
                    new ParkingSpot(1, 2, 1, SpotType.STANDARD,  false),  // car spot
                    new ParkingSpot(1, 2, 2, SpotType.STANDARD,  false),
                    new ParkingSpot(1, 3, 1, SpotType.LARGE,     false)   // bus spot
            ));
        }
    }
}

