package com.samilemir.model;

import java.time.LocalDate;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Passenger extends BaseEntity {
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="birth_of_date", nullable=false)
	private LocalDate birthOfDate;
	
	@Column(name="e_mail", nullable=false, unique=true)
	@NotNull
	private String eMail;
	
	 @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Ticket> tickets;
	 
	 @OneToOne
	 @JoinColumn(name = "user_id", nullable = false, unique = true)
	 private User user; // Her yolcu bir kullanıcıya bağlıdır.

}
