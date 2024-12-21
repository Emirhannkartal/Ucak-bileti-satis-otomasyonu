package com.samilemir.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DtoPassengerIU extends DtoBase{
	
    private String firstName;
    private String lastName;
	private LocalDate birthOfDate;
	private String eMail;
	private Long userId;
}
