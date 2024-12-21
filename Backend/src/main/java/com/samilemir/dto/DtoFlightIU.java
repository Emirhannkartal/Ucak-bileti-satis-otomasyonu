package com.samilemir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoFlightIU extends DtoBase {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long departureAirportId;
    private Long arrivalAirportId;
}
