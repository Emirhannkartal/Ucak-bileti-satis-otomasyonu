package com.samilemir.service;

import com.samilemir.model.Passenger;
import java.util.List;

public interface IPassengerService {
    List<Passenger> getAllPassengers();

    Passenger getPassengerById(Long id);
    
    Passenger getPassengerByUserId(Long userId);

    Passenger createPassenger(Passenger passenger);

    Passenger updatePassenger(Long id, Passenger passenger);

    void deletePassenger(Long id);
}
