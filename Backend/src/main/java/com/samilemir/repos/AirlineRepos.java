package com.samilemir.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samilemir.model.Airline;

@Repository
public interface AirlineRepos extends JpaRepository<Airline, Long> {
}