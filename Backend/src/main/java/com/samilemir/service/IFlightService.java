package com.samilemir.service;

import com.samilemir.model.Flight;

import java.util.List;

public interface IFlightService {

    List<Flight> getAllFlights();

    Flight getFlightById(Long id);

    Flight createFlight(Flight flight);

    Flight updateFlight(Long id, Flight flightDetails);

    void deleteFlight(Long id);
}
