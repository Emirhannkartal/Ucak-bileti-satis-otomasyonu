package com.samilemir.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat extends BaseEntity{

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

}
