package com.samilemir.controller;

import com.samilemir.dto.DtoTicket;
import com.samilemir.dto.DtoTicketIU;
import com.samilemir.model.Flight;
import com.samilemir.model.Passenger;
import com.samilemir.model.Ticket;
import com.samilemir.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @GetMapping
    public List<DtoTicket> getAllTickets() {
        return ticketService.getAllTickets().stream()
                .map(this::convertToDtoTicket)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DtoTicket getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return convertToDtoTicket(ticket);
    }

    @PostMapping
    public DtoTicket createTicket(@RequestBody DtoTicketIU ticketDto) {
        Ticket ticket = convertToEntity(ticketDto);
        Ticket createdTicket = ticketService.createTicket(ticket);
        return convertToDtoTicket(createdTicket);
    }

    @PutMapping("/{id}")
    public DtoTicket updateTicket(@PathVariable Long id, @RequestBody DtoTicketIU ticketDto) {
        Ticket ticket = convertToEntity(ticketDto);
        Ticket updatedTicket = ticketService.updateTicket(id, ticket);
        return convertToDtoTicket(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

    private DtoTicket convertToDtoTicket(Ticket ticket) {
        return new DtoTicket(
                ticket.getSeatNumber(),
                ticket.getPnrCode(),
                ticket.getPassenger().getId(),
                ticket.getFlight().getId()
        );
    }

    private Ticket convertToEntity(DtoTicketIU ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setSeatNumber(ticketDto.getSeatNumber());

        Passenger passenger = new Passenger();
        passenger.setId(ticketDto.getPassengerId());
        ticket.setPassenger(passenger);

        Flight flight = new Flight();
        flight.setId(ticketDto.getFlightId());
        ticket.setFlight(flight);

        return ticket;
    }
}
