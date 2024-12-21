package com.samilemir.controller;

import com.samilemir.dto.DtoFlight;
import com.samilemir.dto.DtoFlightIU;
import com.samilemir.model.Airport;
import com.samilemir.model.Flight;
import com.samilemir.service.IAirportService;
import com.samilemir.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    @Autowired
    private IFlightService flightService;

    @Autowired
    private IAirportService airportService;

    @GetMapping
    public List<DtoFlight> getAllFlights() {
        return flightService.getAllFlights().stream()
                .map(this::convertToDtoFlight)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DtoFlight getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return convertToDtoFlight(flight);
    }

    @PostMapping
    public Flight createFlight(@RequestBody DtoFlightIU dtoFlightIU) {
        Flight flight = convertToFlight(dtoFlightIU);
        return flightService.createFlight(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody DtoFlightIU dtoFlightIU) {
        Flight flight = convertToFlight(dtoFlightIU);
        flight.setId(id);
        return flightService.updateFlight(id,flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    private DtoFlight convertToDtoFlight(Flight flight) {
        return new DtoFlight(
        		flight.getId(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getDepartureAirport().getAirportName(),
                flight.getArrivalAirport().getAirportName()
        );
    }

    private Flight convertToFlight(DtoFlightIU dtoFlightIU) {
        Airport departureAirport = airportService.getAirportById(dtoFlightIU.getDepartureAirportId());
        Airport arrivalAirport = airportService.getAirportById(dtoFlightIU.getArrivalAirportId());
        return new Flight(
                dtoFlightIU.getDepartureTime(),
                dtoFlightIU.getArrivalTime(),
                departureAirport,
                arrivalAirport
        );
    }
}
