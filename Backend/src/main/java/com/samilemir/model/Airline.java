package com.samilemir.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="airlines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Airline extends BaseEntity {
	@Column(name="airline_name", nullable=false, unique=true)
	private String airlineName;
}
