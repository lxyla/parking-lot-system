package com.example.parkinglot.repository;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.SpotType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingpSotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByOccupied();
    List<ParkingSpot> findByOccupiedFalseAndSpotTypeIn(List<SpotType> spotTypes);
}
