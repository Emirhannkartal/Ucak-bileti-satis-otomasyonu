package com.samilemir.service;

import com.samilemir.dto.DtoSeat;
import com.samilemir.dto.DtoSeatIU;

import java.util.List;

public interface ISeatService {
    List<DtoSeat> getAvailableSeatsByFlight(Long flightId);

    DtoSeat createSeat(DtoSeatIU dtoSeatIU);

    DtoSeat updateSeat(Long seatId, DtoSeatIU dtoSeatIU);
}
