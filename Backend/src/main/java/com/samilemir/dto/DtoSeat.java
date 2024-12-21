package com.samilemir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoSeat {
    private Long id;
    private String seatNumber;
    private boolean isAvailable;
    private Long flightId;
}
