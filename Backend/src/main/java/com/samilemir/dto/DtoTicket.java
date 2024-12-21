package com.samilemir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoTicket {
    private String seatNumber;
    private String pnrCode;
    private Long passengerId;
    private Long flightId;
}
