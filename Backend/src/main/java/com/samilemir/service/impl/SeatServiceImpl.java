package com.samilemir.service.impl;

import com.samilemir.dto.DtoSeat;
import com.samilemir.dto.DtoSeatIU;
import com.samilemir.model.Flight;
import com.samilemir.model.Seat;
import com.samilemir.repos.FlightRepos;
import com.samilemir.repos.SeatRepos;
import com.samilemir.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements ISeatService {

    @Autowired
    private SeatRepos seatRepository;

    @Autowired
    private FlightRepos flightRepository;

    @Override
    public List<DtoSeat> getAvailableSeatsByFlight(Long flightId) {
        return seatRepository.findByFlightIdAndIsAvailable(flightId, true).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DtoSeat createSeat(DtoSeatIU dtoSeatIU) {
        Flight flight = flightRepository.findById(dtoSeatIU.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Seat seat = new Seat();
        seat.setSeatNumber(dtoSeatIU.getSeatNumber());
        seat.setAvailable(dtoSeatIU.isAvailable());
        seat.setFlight(flight);

        return convertToDto(seatRepository.save(seat));
    }

    @Override
    public DtoSeat updateSeat(Long seatId, DtoSeatIU dtoSeatIU) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        seat.setSeatNumber(dtoSeatIU.getSeatNumber());
        seat.setAvailable(dtoSeatIU.isAvailable());

        Flight flight = flightRepository.findById(dtoSeatIU.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        seat.setFlight(flight);

        return convertToDto(seatRepository.save(seat));
    }

    private DtoSeat convertToDto(Seat seat) {
        return new DtoSeat(
                seat.getId(),
                seat.getSeatNumber(),
                seat.isAvailable(),
                seat.getFlight().getId()
        );
    }
}
