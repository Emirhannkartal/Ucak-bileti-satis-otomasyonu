package com.samilemir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoTicketIU {
    private String seatNumber;
    private Long passengerId;
    private Long flightId;
}
