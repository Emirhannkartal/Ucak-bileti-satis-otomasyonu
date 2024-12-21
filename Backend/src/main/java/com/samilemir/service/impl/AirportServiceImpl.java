package com.samilemir.service.impl;

import com.samilemir.exception.BaseException;
import com.samilemir.exception.ErrorMessage;
import com.samilemir.exception.MessageType;
import com.samilemir.model.Airport;
import com.samilemir.repos.AirportRepos;
import com.samilemir.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements IAirportService {

    @Autowired
    private AirportRepos airportRepos;

    @Override
    public List<Airport> getAllAirports() {
        return airportRepos.findAll();
    }

    @Override
    public Airport getAirportById(Long id) {
        return airportRepos.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage("Airport not found with ID: " + id, MessageType.HAVALANI_BULUNAMADI)));
    }
    

    @Override
    public Airport createAirport(Airport airport) {
        return airportRepos.save(airport);
    }

    @Override
    public Airport updateAirport(Long id, Airport airportDetails) {
        Airport existingAirport = getAirportById(id);
        existingAirport.setAirportName(airportDetails.getAirportName());
        existingAirport.setAirportCity(airportDetails.getAirportCity());
        existingAirport.setAirportCountry(airportDetails.getAirportCountry());
        return airportRepos.save(existingAirport);
    }

    @Override
    public void deleteAirport(Long id) {
        Airport airport = getAirportById(id);
        airportRepos.delete(airport);
    }
}
