package com.example.parkinglot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.VehicleType;
import com.example.parkinglot.repository.ParkingSpotRepository;
import com.example.parkinglot.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ParkinglotApplicationTests {

	@Test
	void contextLoads() {
	}
}
