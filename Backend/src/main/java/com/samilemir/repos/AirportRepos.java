package com.samilemir.repos;

import com.samilemir.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepos extends JpaRepository<Airport, Long> {
}
