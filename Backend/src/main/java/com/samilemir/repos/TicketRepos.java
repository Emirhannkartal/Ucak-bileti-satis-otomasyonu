package com.samilemir.repos;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.samilemir.model.Ticket;

public interface TicketRepos extends JpaRepository<Ticket, Long> {
    List<Ticket> findByFlightId(Long flightId);

    List<Ticket> findByPassengerId(Long passengerId);
    
    Ticket findByPassengerLastNameAndPnrCode(@Param("lastName") String lastName, @Param("pnrCode") String pnrCode);
    
    boolean existsByPnrCode(String pnrCode);
 
}
