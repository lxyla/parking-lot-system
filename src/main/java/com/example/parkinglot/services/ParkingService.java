package com.example.parkinglot.services;

import com.example.parkinglot.repository.ParkingpSotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // indicates that the class provides some business functionalities
@Transactional // ensures that the method runs within a database transaction
public class ParkingService {

    @Autowired // enables automatic dependency injection
    private ParkingpSotRepository parkingpSotRepository;
}
