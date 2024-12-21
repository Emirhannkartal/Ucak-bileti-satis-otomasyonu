package com.samilemir.service.impl;

import com.samilemir.exception.BaseException;
import com.samilemir.exception.ErrorMessage;
import com.samilemir.exception.MessageType;
import com.samilemir.model.Flight;
import com.samilemir.repos.FlightRepos;
import com.samilemir.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements IFlightService {

    @Autowired
    private FlightRepos flightRepository;

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage("Flight not found with ID: " + id, MessageType.UCUS_BULUNAMADI)));
    }

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight existingFlight = getFlightById(id);
        existingFlight.setDepartureTime(flightDetails.getDepartureTime());
        existingFlight.setArrivalTime(flightDetails.getArrivalTime());
        existingFlight.setDepartureAirport(flightDetails.getDepartureAirport());
        existingFlight.setArrivalAirport(flightDetails.getArrivalAirport());
        return flightRepository.save(existingFlight);
    }

    @Override
    public void deleteFlight(Long id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }
}
