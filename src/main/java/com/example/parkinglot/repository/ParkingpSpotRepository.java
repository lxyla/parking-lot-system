package com.example.parkinglot.repository;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.SpotType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingpSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByOccupiedFalse();
    Optional<ParkingSpot> findByOccupiedFalseAndSpotTypeIn(List<SpotType> spotTypes);
}
