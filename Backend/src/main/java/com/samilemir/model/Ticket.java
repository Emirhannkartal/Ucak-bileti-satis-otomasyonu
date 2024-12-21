package com.samilemir.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Ticket extends BaseEntity {
	
	@Column(name="seat_number", nullable=false)
	private String seatNumber;
	
	
	@Column(name="pnr_code",nullable=false, unique=true)
	private String pnrCode;
	
	@ManyToOne
	@JoinColumn(name="passenger_id", nullable=false)
	private Passenger passenger;
	
	@ManyToOne
	@JoinColumn(name="flight_id", nullable=false)
	private Flight flight;
	
	@Column(name = "checked_in", nullable = false)
	private boolean checkedIn;

	

	private Double amount;
	private String currencyType;
	private String ticketStatus;

}
