package com.samilemir.controller;

import com.samilemir.dto.DtoSeat;
import com.samilemir.dto.DtoSeatIU;
import com.samilemir.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {

    @Autowired
    private ISeatService seatService;
    
    @GetMapping("/available/{flightId}") //api/v1/seats/available/{flightId}
    public List<DtoSeat> getAvailableSeatsByFlight(@PathVariable Long flightId) {
        return seatService.getAvailableSeatsByFlight(flightId);
    }

    @PostMapping //api/v1/seats
    public DtoSeat createSeat(@RequestBody DtoSeatIU dtoSeatIU) {
        return seatService.createSeat(dtoSeatIU);
    }

    @PutMapping("/{seatId}") //api/v1/seats/{seatId}
    public DtoSeat updateSeat(@PathVariable Long seatId, @RequestBody DtoSeatIU dtoSeatIU) {
        return seatService.updateSeat(seatId, dtoSeatIU);
    }
}
