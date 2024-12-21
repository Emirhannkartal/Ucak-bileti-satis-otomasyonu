package com.samilemir.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="airport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Airport extends BaseEntity {
	
	@Column(name="airport_name", nullable=false)
	private String airportName;
	
	@Column(name="airport_city", nullable=false)
	private String airportCity;
	
	@Column(name="airport_country", nullable=false)
	private String airportCountry;

}
