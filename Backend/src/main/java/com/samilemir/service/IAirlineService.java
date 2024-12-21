package com.samilemir.service;

import java.util.List;

import com.samilemir.model.Airline;

public interface IAirlineService {

    List<Airline> getAllAirlines();

    Airline getAirlineById(Long id);

    Airline createAirline(Airline airline);

    Airline updateAirline(Long id, Airline airlineDetails);

    void deleteAirline(Long id);
}
