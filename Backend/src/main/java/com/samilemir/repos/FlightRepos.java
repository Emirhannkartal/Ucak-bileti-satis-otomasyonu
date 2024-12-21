package com.samilemir.repos;

import com.samilemir.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepos extends JpaRepository<Flight, Long> {
}
