package com.example.parkinglot.medium;

import com.example.parkinglot.exception.IllegalParkingException;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.SpotType;
import com.example.parkinglot.model.TicketStatus;
import com.example.parkinglot.model.VehicleType;
import com.example.parkinglot.repository.ParkingSpotRepository;
import com.example.parkinglot.services.ParkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class ParkingServiceIntegrationTest {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Test
    void busNeedsFiveConsecutiveFreeSpots() {
        var spots = parkingService.findAvailableSpots(VehicleType.BUS);

        assertThat(spots)
                .extracting(ParkingSpot::getSpotNumber)
                .containsExactly(1, 2, 3, 4, 5);

        ParkingSpot middleSpot = spots.get(2);
        middleSpot.setOccupied(true);
        parkingSpotRepository.saveAndFlush(middleSpot);

        assertThat(parkingService.findAvailableSpots(VehicleType.BUS))
                .isEmpty();
    }

    @Test
    void parksOneBusAndRejectsASecond() {
        var ticket = parkingService.parkVehicle("BUS-001", VehicleType.BUS);
        var parkedSpots = ticket.getParkingSpots();

        assertThat(ticket.getId()).isPositive();
        assertThat(ticket.getLicensePlate()).isEqualTo("BUS-001");
        assertThat(ticket.getVehicleType()).isEqualTo(VehicleType.BUS);
        assertThat(ticket.getStatus()).isEqualTo(TicketStatus.ACTIVE);
        assertThat(ticket.getEntryTime()).isNotNull();
        assertThat(ticket.getExitTime()).isNull();

        assertThat(parkedSpots)
                .extracting(ParkingSpot::getSpotNumber)
                .containsExactly(1, 2, 3, 4, 5);

        parkingSpotRepository.flush();

        assertThat(parkingSpotRepository.findByOccupiedFalse())
                .hasSize(4)
                .noneMatch(spot -> spot.getSpotType() == SpotType.LARGE);

        assertThatThrownBy(() ->
                parkingService.parkVehicle("BUS-002", VehicleType.BUS))
                .isInstanceOf(IllegalParkingException.class);
    }
}