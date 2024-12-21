package com.samilemir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samilemir.exception.BaseException;
import com.samilemir.exception.ErrorMessage;
import com.samilemir.exception.MessageType;
import com.samilemir.model.Airline;
import com.samilemir.repos.AirlineRepos;
import com.samilemir.service.IAirlineService;

@Service
public class AirlineServiceImpl implements IAirlineService {

    @Autowired
    private AirlineRepos airlineRepos;

    @Override
    public List<Airline> getAllAirlines() {
        return airlineRepos.findAll();
    }

    @Override
    public Airline getAirlineById(Long id) {
        return airlineRepos.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage("Airline not found with ID: " + id, MessageType.HAVAYOLU_BULUNAMADI)));
    }

    @Override
    public Airline createAirline(Airline airline) {
        return airlineRepos.save(airline);
    }

    @Override
    public Airline updateAirline(Long id, Airline airlineDetails) {
        Airline existingAirline = getAirlineById(id);
        existingAirline.setAirlineName(airlineDetails.getAirlineName());
        return airlineRepos.save(existingAirline);
    }

    @Override
    public void deleteAirline(Long id) {
        Airline airline = getAirlineById(id);
        airlineRepos.delete(airline);
    }
}
