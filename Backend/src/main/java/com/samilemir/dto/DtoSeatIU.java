package com.samilemir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoSeatIU {
    private String seatNumber;
    private boolean isAvailable=true;
    private Long flightId;
}
