package com.samilemir.repos;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samilemir.model.Passenger;

public interface PassengerRepos extends JpaRepository<Passenger, Long> {
	Optional<Passenger> findByUserId(Long userId);
    boolean existsByUserId(Long userId);

  
}
