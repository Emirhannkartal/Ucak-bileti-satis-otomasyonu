package com.samilemir.service;

import com.samilemir.model.Airport;

import java.util.List;

public interface IAirportService {

    List<Airport> getAllAirports();

    Airport getAirportById(Long id);

    Airport createAirport(Airport airport);

    Airport updateAirport(Long id, Airport airportDetails);

    void deleteAirport(Long id);
}
