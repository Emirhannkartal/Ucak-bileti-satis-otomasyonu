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
public class DtoFlight {
	private Long id;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String departureAirportName;
    private String arrivalAirportName;
}
