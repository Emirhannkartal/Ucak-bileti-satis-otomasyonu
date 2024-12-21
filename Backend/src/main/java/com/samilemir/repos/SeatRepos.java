package com.samilemir.repos;

import com.samilemir.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepos extends JpaRepository<Seat, Long> {
    List<Seat> findByFlightIdAndIsAvailable(Long flightId, boolean isAvailable);
}
