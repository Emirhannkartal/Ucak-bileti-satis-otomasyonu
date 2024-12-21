package com.samilemir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import com.samilemir.model.Airline;
import com.samilemir.service.IAirlineService;

@RestController
@RequestMapping("/api/v1/airlines")
public class AirlineController {

    @Autowired
    private IAirlineService airlineService;

    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public Airline getAirlineById(@PathVariable Long id) {
        return airlineService.getAirlineById(id);
    }

    @PostMapping
    public Airline createAirline(@RequestBody Airline airline, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AccessDeniedException("Authorization token is missing or invalid");
        }

        Airline createdAirline = airlineService.createAirline(airline);
        return createdAirline;
    }

    @PutMapping("/{id}")
    public Airline updateAirline(@PathVariable Long id, @RequestBody Airline airline) {
        return airlineService.updateAirline(id, airline);
    }

    @DeleteMapping("/{id}")
    public void deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
    }
}
