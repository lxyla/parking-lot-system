package com.example.parkinglot.services;

import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.parkinglot.model.SpotType;
import com.example.parkinglot.model.VehicleType;
import com.example.parkinglot.exception.IllegalParkingException;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ParkingService {

    private final ParkingSpotRepository parkingSpotRepository;
    private static final int BUS_SPOTS_NEEDED = 5;

    public ParkingService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public List<ParkingSpot> getAvailableParkingSpots() {
        return parkingSpotRepository.findByOccupiedFalse();
    }

    public List<ParkingSpot> findAvailableSpots(VehicleType vehicleType) {
        List<ParkingSpot> availableSpots = getAvailableParkingSpots();

        return switch (vehicleType){
            case MOTORCYCLE ->  availableSpots.stream()
                    .limit(1)
                    .toList();
            case CAR -> availableSpots.stream()
                    .filter(spot -> spot.getSpotType() == SpotType.COMPACT
                        || spot.getSpotType() == SpotType.LARGE)
                    .limit(1)
                    .toList();
            case BUS -> findConsecutiveLargeSpots(availableSpots);
        };
    }

    private List<ParkingSpot>  findConsecutiveLargeSpots(List<ParkingSpot> availableSpots) {
        List<ParkingSpot> largeSpots = availableSpots.stream()
                .filter(spot -> spot.getSpotType() == SpotType.LARGE)
                .sorted(Comparator.comparingInt(ParkingSpot::getFloorNumber)
                        .thenComparingInt(ParkingSpot::getRowNumber)
                        .thenComparingInt(ParkingSpot::getSpotNumber))
                .toList();
        int consecutive = 0;
        ParkingSpot previous = null;

        for (int index = 0; index < largeSpots.size(); index++) {
            ParkingSpot current = largeSpots.get(index);

            if (previous != null
                    && current.getFloorNumber() == previous.getFloorNumber()
                    && current.getRowNumber() == previous.getRowNumber()
                    && current.getSpotNumber() == previous.getSpotNumber() + 1) {
                consecutive++;
            } else {
                consecutive = 1;
            }

            if (consecutive == BUS_SPOTS_NEEDED) {
                return List.copyOf(largeSpots.subList(index - consecutive + 1, index + 1));
            }

            previous = current;
        }

        return List.of();
    }

    public List<ParkingSpot> parkVehicle(VehicleType vehicleType) {
        List<ParkingSpot> spots = findAvailableSpots(vehicleType);

        if (spots.isEmpty()) {
            throw new IllegalParkingException(
                    "No compatible parking spots available for " + vehicleType);
        }

        for (ParkingSpot spot : spots) {
            spot.setOccupied(true);
        }

        return spots;
    }

}
